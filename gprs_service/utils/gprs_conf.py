import serial
import time


def reader(t):
    out = ''
    time.sleep(t)
    while console.inWaiting() > 0
        out += console.read(1)
    if out != '':
        print ">>" + out


def gprs_conf():
    console = serial.Serial(
        port='/dev/ttyAMA0',
        baudrate=9600,
    )
    console.isOpen()
    time.sleep(1)
    console.write('at+cfun=1,1\r\n')
    reader(1)
    console.write('at+creg=1\r\n')
    reader(1)
    console.write('at+cgatt=1\r\n')
    reader(1)
    console.write('at+cstt="internet.cp","",""\r\n')
    reader(1)
    console.write('at+ciicr\r\n')
    reader(1)
    console.write('at+cifsr\r\n')
