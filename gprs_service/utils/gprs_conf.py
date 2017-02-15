import serial
import time
import serial_init

class Gprs_conf():

    def reader(self,t):
        out = ''
        time.sleep(t)
        while self.console.inWaiting() > 0:
            out += self.console.read(1)
        if out != '':
            print ">>" + out
        time.sleep(10)

    def setConf(self):    
        Serial_init.console.isOpen()
        time.sleep(1)
        Serial_init.console.write('at+cfun=1,1\r\n')
        self.reader(2)
        Serial_init.console.write('at+creg=1\r\n')
        self.reader(2)
        Serial_init.console.write('at+cgatt=1\r\n')
        self.reader(2)
        Serial_init.console.write('at+cstt="internet.cp","",""\r\n')
        self.reader(2)
        Serial_init.console.write('at+ciicr\r\n')
        self.reader(2)
        Serial_init.console.write('at+cifsr\r\n')
        self.reader(2)


conf = Gprs_conf()
conf.setConf()