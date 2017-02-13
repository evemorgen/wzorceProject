# GPRS 




# 1. AT command
To test AT command use prepared python script
```sh
sudo python pyserial_example.py
```
script connets to port `/dev/ttyAMA0` with rate `9600` using python serial.

#### GPRS configuration
To configure GPRS follow this commands:

| Command | Description | Response |
| ------- | ----------- | -------- |
| at+cfun=1,1 | Restart module | OK |
| at+creg=1 | Enable network registration | OK |
| at+cgatt=1 | Attach GPRS service | OK |
| at+cstt="internet.cp","","" | Set APN "apn","user","password" | OK |
| at+ciicr | Start wireless connection with GPRS | OK |
| at+cifsr | Get ip assigned to module, needed before trying to make any connections | ip adress |



# 2. API
