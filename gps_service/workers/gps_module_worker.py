import logging

from tornado.gen import coroutine
from pigpio import pi, INPUT

from utils import YieldPeriodicCallback, Config


class GpsModuleWorker(YieldPeriodicCallback):
    def __init__(self):
        self.config = Config()
        self.rx_pin = self.config['rx_pin']
        self.configure_raspi()
        logging.info('starting gps worker')
        YieldPeriodicCallback.__init__(self, self.run, self.config['worker_period'], faststart=True)

    def configure_raspi(self):
        self.raspi = pi()
        self.run_rumber = 1
        self.raspi.set_mode(self.rx_pin, INPUT)
        self.raspi.bb_serial_read_open(self.rx_pin, 9600, 8)
        logging.info('software serial initialized')

    @coroutine
    def run(self):
        (count, data) = pi.bb_serial_read(self.rx_pin)
        if count > 0:
            print("-*-\n%d\n\n%s\n-#-" % (self.run_number, data.decode('utf-8')))
        self.run_number += 1
