import serial
import time
import serial_init

class Gprs_conf():

    def __init__(self, Serial_init):
        self.ser = Serial_init.console

    def reader(self,t):
        out = ''
        time.sleep(t)
        while self.ser.inWaiting() > 0:
            out += self.ser.read(1)
        if out != '':
            print ">>" + out
        time.sleep(10)

    def setConf(self):    
        self.ser.isOpen()
        time.sleep(1)
        self.ser.write('at+cfun=1,1\r\n')
        self.reader(2)
        self.ser.write('at+creg=1\r\n')
        self.reader(2)
        self.ser.write('at+cgatt=1\r\n')
        self.reader(2)
        self.ser.write('at+cstt="internet.cp","",""\r\n')
        self.reader(2)
        self.ser.write('at+ciicr\r\n')
        self.reader(2)
        self.ser.write('at+cifsr\r\n')
        self.reader(2)


conf = Gprs_conf()
conf.setConf()