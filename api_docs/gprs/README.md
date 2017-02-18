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
API provides HTTP GET and POST, sending SMS and restarting and configurating module.
You can choose action by specyfying "action" in json.
Available actions are:
	-conf
	-get
	-post
	-sms


## conf
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        This method does not requires any parameters, you need to pass an object with: - {"method": "conf"}
        </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />?

</table>

Example usage:

```
curl -X POST 192.168.1.123:6969 -H'Content-type: application/json' -d'{"method": "conf"}'
```

Return:
```
{
    "result": [
        "at+cfun=1,1\r\r\nOK\r\n",
        "at+creg=1\r\r\nOK\r\n\r\n+CREG: 1\r\n",
        "at+cgatt=1\r\r\nOK\r\n",
        "at+cstt=\"internet.cp\",\"\",\"\"\r\r\nOK\r\n",
        "at+ciicr\r\r\nOK\r\n",
        "at+cifsr\r\r\n100.94.96.145\r\n",
        "at+cmgf=1\r\r\nOK\r\n"
    ],
    "status": "OK"
}
```


## get
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        This method requires additional parameter url you want to GET, you need to pass an object with: - {"method": "get","url": "your url"}
        </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />?

</table>

Example usage:

```
curl -X POST 192.168.1.123:6969/ -d '{"method": "get", "url": "google.com/"}'
```

Return:
```
{
"result": [
    "\r\n+CMGS: 54\r\n\r\nOK\r\nat+cipstart=\"TCP\",\"google.com\",\"80\"\r\r\nOK\r\n\r\nCONNECT OK\r\n",
    "at+cipsend\r\r\n> ",
    "GET / HTTP/1.0\r\n\r\n",
    "\u001a\r\nSEND OK\r\nHTTP/1.0 302 Found\r\nCache-Control: private\r\nContent-Type: text/html; charset=UTF-8\r\nLocation: http://www.google.pl/?gfe_rd=cr&ei=obioWNLqEdCv8wfrtaqYDA\r\nContent-Length: 258\r\nDate: Sat, 18 Feb 2017 21:12:01 GMT\r\n\r\n<HTML><HEAD><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">\n<TITLE>302 Moved</TITLE></HEAD><BODY>\n<H1>302 Moved</H1>\nThe document has moved\n<A HREF=\"http://www.google.pl/?gfe_rd=cr&amp;ei=obioWNLqEdCv8wfrtaqYDA\">here</A>.\r\n</BODY></HTML>\r\n"
    ],
    "status": "OK"
}
```


## post
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        This method requires additional parameter url you want to post, headers list to set and data you want to send, you need to pass an object with: - {"method": "get","url": "your url", "headers": ["h1","h2","h3"], "data": "your data"}
        </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />?

</table>

Example usage:

```
curl -X POST 192.168.1.123:6969/ -d '{"method": "post", "url": "student.agh.edu.pl/~cvmorgen/skrypt_ipki/index.php", "headers": ["Content-Type: application/x-www-form-urlencoded"], "data": "ip=POSTtest"}'
```

Return:
```
{
    "status": "OK",
    "result": [
        "at+cipstart=\"TCP\",\"student.agh.edu.pl\",\"80\"\r\r\nOK\r\n\r\nCONNECT OK\r\n",
        "at+cipsend\r\r\n> ",
        "POST /~cvmorgen/skrypt_ipki/index.php HTTP/1.1\r\n",
        "Host: student.agh.edu.pl\r\n",
        "Content-Length: 13\r\n",
        "Content-type: application/x-www-form-urlencoded\r\n\r\n",
        "ip=hello post\r\n",
        "\u001a\r\nSEND OK\r\nHTTP/1.1 200 OK\r\nDate: Sat, 18 Feb 2017 21:19:30 GMT\r\nServer: Apache/2.2.31 (Unix) mod_ssl/2.2.31 OpenSSL/0.9.8zh\r\nX-Powered-By: PHP/5.3.29\r\nTransfer-Encoding: chunked\r\nContent-Type: text/html\r\n\r\n4d0e\r\nGET - 83.7.83.205 <br>GET - hello_from_raspi <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205 <br>GET - 83.7.83.205",
        "at+cipclose\r\r\nOK\r\n"
    ]
}
```


## sms
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        This method requires additional parameter number data you want to send, you need to pass an object with: - {"method": "sms","number": "+48_your_number", "text": "text_you_want_to_send"}
        </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />?

</table>

Example usage:

```
curl -X POST 192.168.1.123:6969/ -d '{"method": "sms","number": "+48XXXXXXXXX", "text": "hello""}'
```

Return:
```
{
    "result": [
        "at+cmgf=1\r\r\nOK\r\n",
        "at+cmgs=\"+48XXXXXXXXX\"\r\r\n> ",
        "hello\r\n> "
    ],
    "status": "OK"
}
```
