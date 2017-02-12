import logging

from tornado.gen import coroutine
from pigpio import pi, INPUT

from utils import YieldPeriodicCallback
from utils import Config


class GpsModuleWorker(YieldPeriodicCallback):
    def __init__(self, coords):
        self.coords = coords
        self.config = Config()
        self.rx_pin = self.config['rx_pin']
        self.configure_raspi()
        self.run_number = 1
        logging.info('starting gps worker')
        YieldPeriodicCallback.__init__(self, self.run, self.config['worker_period'], faststart=True)

    def configure_raspi(self):
        self.raspi = pi()
        self.raspi.bb_serial_read_close(self.rx_pin)
        self.raspi.set_mode(self.rx_pin, INPUT)
        self.raspi.bb_serial_read_open(self.rx_pin, 9600, 8)
        logging.info('software serial initialized')

    @coroutine
    def run(self):
        (count, data) = self.raspi.bb_serial_read(self.rx_pin)
        data = data.decode('utf-8')
        if count > 0:
            ramki = data.split('\r\n')
            for ramka in ramki:
                if ramka != '':
                    self.coords.add_frame(ramka)
                    print('%s %s %s' % (ramka, ramka[0] == '$', ramka[-3] == '*'))
            # print("-*-\n%d\n\n%s\n-#-" % (self.run_number, ramki))
        self.run_number += 1
        if self.run_number % 10 == 0:
            print(self.coords.get_last_n_frames(10))
