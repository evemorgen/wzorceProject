import serial
import time


class Gprs_conf():

    def __init__(self):
        self.console = serial.Serial(
        port='/dev/ttyAMA0',
        baudrate=9600,
    )

    def reader(self,t):
        out = ''
        time.sleep(t)
        while self.console.inWaiting() > 0:
            out += self.console.read(1)
        if out != '':
            print ">>" + out
        time.sleep(10)

    def setConf(self):    
        self.console.isOpen()
        time.sleep(1)
        self.console.write('at+cfun=1,1\r\n')
        self.reader(2)
        self.console.write('at+creg=1\r\n')
        self.reader(2)
        self.console.write('at+cgatt=1\r\n')
        self.reader(2)
        self.console.write('at+cstt="internet.cp","",""\r\n')
        self.reader(2)
        self.console.write('at+ciicr\r\n')
        self.reader(2)
        self.console.write('at+cifsr\r\n')
        self.reader(2)
        self.console.write('at+cmgf=1\r\n')


conf = Gprs_conf()
conf.setConf()