import serial
import time


class Gprs_post:
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
            print (">>" + out)
        time.sleep(2)


    def send(self,url):
        head, req = url.split('/', 1)
        req, params = req.split('?', 1)
        # print head, req, params, len(params)
        self.console.isOpen()
        time.sleep(1)
        self.console.write('at+cipstart="TCP","'+head+'","80"\r\n')
        self.reader(2)
        self.console.write('at+cipsend\r\n')
        self.reader(2)
        self.console.write('POST /%s HTTP/1.1\r\n' % req)
        time.sleep(0.5)
        self.console.write('Host: %s\r\n' % head)
        time.sleep(0.5)
        self.console.write('Content-Length: %d\r\n' % len(params))
        time.sleep(0.5)
        self.console.write('Content-Type: application/x-www-form-urlencoded\r\n\r\n')
        time.sleep(0.5)
        self.console.write(params + '\r\n')
        time.sleep(0.5)
        self.console.write(str(unichr(26)))
        self.reader(2)
        



gprs = Gprs_post()
gprs.send('student.agh.edu.pl/~cvmorgen/skrypt_ipki/index.php?ip=POSTdupa')
