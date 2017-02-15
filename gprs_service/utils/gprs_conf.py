import serial
import time
import serial_init

class Gprs_conf(ser):

    def reader(self,t,ser):
        out = ''
        time.sleep(t)
        while ser.console.inWaiting() > 0:
            out += ser.console.read(1)
        if out != '':
            print ">>" + out
        time.sleep(10)

    def setConf(self,ser):    
        ser.console.isOpen()
        time.sleep(1)
        ser.console.write('at+cfun=1,1\r\n')
        self.reader(2)
        ser.console.write('at+creg=1\r\n')
        self.reader(2)
        ser.console.write('at+cgatt=1\r\n')
        self.reader(2)
        ser.console.write('at+cstt="internet.cp","",""\r\n')
        self.reader(2)
        ser.console.write('at+ciicr\r\n')
        self.reader(2)
        ser.console.write('at+cifsr\r\n')
        self.reader(2)


conf = Gprs_conf(Serial_init)
conf.setConf(Serial_init)