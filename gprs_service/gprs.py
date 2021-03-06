import os
import logging

from tornado.web import Application
from tornado.ioloop import IOLoop

from utils import logging_config
from utils import Service
from utils import AtCommand
from handlers import HealthCheckHandler
from handlers import GprsHandler


PORT = 6969


def make_app(at):
	return Application([
		(r'/healtcheck', HealthCheckHandler),
		(r'/', GprsHandler, {"at": at})
	])


if __name__ == '__main__':
	logging_config()
	logging.info("start gprs module")
	at = AtCommand()
	methods = [method for method in dir(at) if callable(getattr(at, method)) and method[0] != '_']
	service = Service('192.168.1.123', PORT, 'GPRS', methods)
	service.register_interface()
	app = make_app(at)
	app.listen(PORT)
	IOLoop.current().start()