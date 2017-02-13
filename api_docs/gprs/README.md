# GPRS 




# 1. AT command
To test AT command use prepared python script
```sh
sudo python pyserial_example.py
```
script connets to `/dev/ttyAMA0` with rate `9600` using python serial.


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


#### Example of usage for TCP HTTP request.
Follow command written below :


 - Start TCP connection with student.agh.edu.pl on port 80.
```
at+cipstart="TCP","student.agh.edu.pl","80"
```
Response :
```
OK
CONNECTION OK
```


 - Indicate that we will be making HTTP connection, after command prompt should be seat as ">".
```
at+cipsend
``` 
Response:
```
>
```


 - Make HTTP request.
```
GET /~cvmorgen/skrypt_ipki/index.php?ip=dupa HTTP/1.0
``` 
press Enter twice and
```
WYSLIJ
```
WYSLIJ is equivalent screen Ctrl+z

Response:
```
SEND OK
HTTP/1.1 200 OK
Date: Mon, 13 Feb 2017 17:03:19 GMT
Server: Apache/2.2.31 (Unix) mod_ssl/2.2.31 OpenSSL/0.9.8zh
X-Powered-By: PHP/5.3.29
Connection: close
Content-Type: text/html

83.30.203.140 <br>83.30.203.140 <br>83.30.203.140 <br>83.30.203.140 <br>83.30.203.140 <br>83.30.203.140 <br>83.30.203.140 <br>83.30.203.140 <br>83.30.203.140 <br>83.30.203.140 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>dupa <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>dupa <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>dupa <br>83.5.244.208 <br>dupa <br>dupa <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>dupa <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>83.5.244.208 <br>213.180.148.5 <br>83.5.244.208 <br>83.5.244.208 <br>

CLOSED
```


#### Example of usage for sending SMS
Follow commands written below:

 - Make sure network registration is enable:
```
at+creg=1
```
Response:
```
OK
```

 - Set modem in TEXT MODE
```
at+cmgf=1
```
Response:
```
OK
```
 - To send SMS use command with number you want to send SMS
```
at+cmgs="+48_number"
```
Response:
```
>
```
Now type your message, press Enter and
```
WYSLIJ
```
WYSLIJ is equivalent screen Ctrl+z

Wait moment and you get response:
```
+CMGS: 44

OK
```


# 2. API
