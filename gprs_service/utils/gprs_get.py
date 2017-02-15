import serial
import time


class Gprs_get:
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


    def send(self,url):
        head, req = url.split('/', 1)
        req = '/' + req
        print head
        print req



gprs = Gprs_get()
gprs.send('student.agh.edu.pl/~cvmorgen/skrypt_ipki/index.php?ip=dupa')
