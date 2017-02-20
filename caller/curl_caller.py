import time
import subprocess


while True:
    output = subprocess.getoutput(["curl", "-X", "POST 192.168.1.123:12345/request", "-d", '{"service": "GPS", "method": "get_raw_frames", "data": {"n": 10}}'], stderr=subprocess.STDOUT)
    print (output)
    # here wyslanie
    time.sleep(30)


