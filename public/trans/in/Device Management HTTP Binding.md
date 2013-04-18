##Introduction

This document defines the binding requirements for communicating DM Messages over the Hypertext Transfer Protocol
(HTTP) as defined by [RFC2616]. HTTP is an application-level protocol for distributing information between two Internet
based applications. HTTP can be an ideal protocol for building wide area, distributed systems, such as the collaborative
framework of the World-Wide Web (Web) environment. HTTP is expected to be the primary wireline protocol used by DM
Clients and servers to communicate with each other. In addition, as wireless networks increase in bandwidth (e.g.,
deployment of GPRS networks) and are adapted to support the IP protocol, HTTP will become the preferred application
protocol for connecting wireless DM Clients and servers.

The HTTP protocol defines a request/response form of communication between two network computers supporting HTTP
applications. Normally, the originator of the request is called the HTTP client. And normally, the recipient of the request is
called the HTTP server.

There are emerging Internet standards for notification or "push" technologies that will allow HTTP servers to also originate
HTTP requests. However, this technology is not currently included in this version of the specification.

HTTP is an ideal protocol for connecting a DM client to a DM Server; and vice a versa. Several MIME media types have
been registered for DM. This makes it adapted for transfer over HTTP. HTTP is widely supported across the Internet. There
are numerous examples of common implementations of both client and server HTTP applications. HTTP servers can be
adapted to support new HTTP-based application in a straightforward manner. DM Messages can be transferred across the
Internet and pass through "firewalls", at the perimeter of individual intranets with relative ease.

HTTP communication usually takes place over a TCP/IP connection. The default TCP port for HTTP is port 80, the default
port for HTTPS / SSL / TLS is 443, but other unregistered ports can also be used. HTTP can also be implemented on top of
any other reliable transport service.

An HTTP request message consists of start line containing a request method, target URI, and protocol version; followed by a
MIME-like request header lines containing meta-information about the request; followed by a blank line; followed by a
possible MIME entity body content. The server responds with a status line, including the message's protocol version and a
response code; followed by MIME-like response header lines containing server information and entity meta-information;
followed by a blank line; followed by an optional MIME entity body content.

##HTTP Bindings

The following sections define the requirements for the binding of DM Messages to HTTP.

###TCP Transport Service

HTTP communication usually takes place over a TCP connection. This binding does not require, but does assume such a
connection. If the HTTP communication takes place over another transport service, then requirements similar to those
defined here for TCP need to be followed. The following sections describe requirements for connection, disconnection and
abort of the TCP connection.

####Connection

Prior to an HTTP client connecting to an HTTP server, the DM Client makes a TCP connection using the TCP open
operation between the client machine and the server machine. The client can use the Internet address resolution for the HTTP
connection URL. The default port is 80. However, another port can be used. The choice of the appropriate port can be a
requirement of the provisioning of the HTTP client to the HTTP server. After creating a successful TCP connection between
the client and server machines, the HTTP connection between the client and server machines can be established by the DM
Client. It cannot be assumed that the TCP connection is kept open between each HTTP request and response sequence (i.e.,
HTTP version 1.1 or higher protocol is being used). Even though HTTP version 1.1 presumes persistent connections, there
can have occurred an anomaly such as a timeout condition or "denial of service" counter-measures on the origin server.
Hence, the DM Client SHOULD be prepared for reconnection between HTTP requests.

####Connection Options

The default port is 80. However, the provisioning of the HTTP client to the HTTP server can require use of another port.
Persistent connections are supported by this specification and presumed by HTTP version 1.1, but are not REQUIRED by
implementations conforming to this specification.

####Disconnection

The DM Client is responsible for terminating the connection with a TCP close operation, when the connection is no longer
needed.
If a persistent connection exists between HTTP requests, the HTTP connection is closed by the HTTP client after receipt of
the HTTP response corresponding to the last DM request in the synchronization session (i.e., the DM request in the last DM
package containing a Final element type specified at the end of the SyncBody).

####Abort

Sometimes abnormal conditions arise which requires an application to break the TCP connection. In these cases, the TCP
reset operation is initiated to terminate the TCP connection.

####Timeouts

In the case of a server timeout, the DM Client SHOULD establish a new HTTP session with the HTTP server and attempt to
resend the current DM package, beginning with the first DM command for which the DM Client has not received an
acknowledgement. In the event that the DM Client requested that no responses be sent, the DM Client SHOULD begin
retransmitting with the first DM command in the DM package.

###Exchanging DM Messages

Once an HTTP connection has been established, one or more DM Message are transferred over the connection, by the DM
Client, in the entity body of HTTP requests or received from the server in the entity body of HTTP responses.

The POST method is used to transfer the DM Message in an HTTP request. Content for odd numbered packages (i.e. Client
→ Server direction) is carried in HTTP Post while content for all even numbered packages (i.e. Server → Client direction) is
carried in HTTP Response, as shown in the following figure.

![figure1](figure1.png)

Figure 1: HTTP Binding for OMA-DM

The body of the HTTP request MUST always include a DM Message. The content-type for the body MUST be the
appropriate DM Message content type.

Generally, the Request-URI, in the start line, is the URI "path" component of the intended recipient resource for the request.
The URI of the origin server is specified by the value in the Host request header.

The Request-URI can also be "*" to indicate that the request is intended for the origin server indicated by the absolute URI
specified in the Host request header. In this case, the origin server would be the DM Server. However, in general, the DM
Server will be a resource (e.g., servlet) under the origin server.

The HTTP response will always include the transport response status code. The body of the HTTP Response MUST always
include a DM Message. The content-type for the body SHOULD be either the "application/vnd.syncml.dm+xml" or
"application/vnd.syncml.dm+wbxml".

####Single Message Per Package

The following is a snippet of the minimal HTTP start line and request headers for a simple HTTP request where the body of
the request is the clear-text, DM Message media type.

	POST ./servlet/dm HTTP/1.1
	Host: www.devicemgmt.org
	Content-Type: application/vnd.syncml.dm+xml; charset="utf-8"
	Content-Length: 1023
	Accept: application/vnd.syncml.dm+xml

Were the body, the binary, WBXML DM Message media type [DMREPPRO], then no content encoding is necessary. HTTP
does not support the Content-Transfer-Encoding of MIME, in any case.

The following is a snippet of the minimal HTTP for an example for a simple HTTP response where the body in the response
is the clear-text, DM Message media type, as specified in the Accept request header in the HTTP request.
	
	HTTP/1.1 200 OK
	Content-Type: application/vnd.syncml.dm+xml; charset="utf-8"
	Content-Length: 1023
	-- HTTP body, represented in a format consistent with the DM Message
	type follows --

####Multiple Messages Per DM Package

Each DM Message MUST be transferred as a DM MIME media type within the body of a HTTP request or response. When
there are multiple DM Messages per DM package, each message is transferred in a separate HTTP request or response;
depending on whether it is a DM request or response.

The recipient of a DM package can determine if there are more DM Messages in the package by the absence of the Final
element in the body of the last received DM Message. When the recipient receives a DM Message with the Final element, it
is the final message within that DM package.

This version of the specification does NOT support transferring DM Messages across HTTP using a "multipart” MIME
media type.

###Transport Commands

HTTP uses a number of types of commands. The following sections specify static conformance requirements for use of these
commands in the DM HTTP binding.

####Methods

The following table summarizes the support for the HTTP methods in the DM HTTP binding.

Method
OPTIONS
GET
HEAD
POST
PUT
DELETE
TRACE
CONNECT
Client Requirements
MAY
MAY
MAY
MUST
MAY
MAY
MAY
MAY
Server Requirements
MAY
MAY
MAY
MUST
MAY
MAY
MAY
MAY

The DM Client MUST use the POST or MAY use the CONNECT method (if supported) to send DM requests to the DM
Server. The CONNECT method is used to initiate an SSL [SSL] / TLS [RFC4346] session to authenticate the HTTP client to
the HTTP server. A typical HTTP request start line would look like the following:

	POST ./servlet/dm HTTP/1.1
	Host: http://www.dm.host.com

In this example, the HTTP client is specifying a "./servlet/dm" path component for the Request-URI, which is a particular
DM resource on the HTTP server. The "Host" HTTP header field is needed to specify the absolute URI for the HTTP server.

The other HTTP methods MAY be supported by implementations conforming to this specification. However, they are not
used at this time by the DM Client.

####General Headers

The following table summarizes the support for the HTTP general headers in the DM HTTP binding.

General Header
Cache-Control
Connection
Content-Encoding
Date
Pragma
Trailer
Transfer-Encoding
Upgrade
Via
Warning
Client Requirements
MUST
MAY
MAY
MAY
MAY
MAY
MUST
MAY
MAY
MAY
Server Requirements
MUST
MAY
MAY
MAY
MAY
MAY
MUST
MAY
MAY
MAY

The Cache-Control general header is used to force control over the caching mechanisms in the request/response chain
between the HTTP client and the HTTP server. Implementations conforming to this specification MUST support this header.
Use of this header with the no-store parameter will assure that SyncML messages sent by the DM Client and DM Server are
not stored in cache storage. This will greatly assure that DM Server and DM Client are processing the messages sent by the
DM Clients and DM Servers, respectively. The following is an example of the typical specification for this header:

	Cache-Control: no-store

In addition, implementations conforming to this specification MUST support the private Cache-Control option to assure that
responses do not get cached and possibly used by agents other than the DM Client agent or the DM Server agent.

	Cache-Control: private

The Content-Encoding header is used as a modifier to the media-type. When present, its value indicates what additional
content coding have been applied to the entity-body, and thus what decoding mechanisms must be applied in order to obtain
the media-type referenced by the Content-Type header field. Content-Encoding is primarily used to allow a document to be
compressed without losing the identity of its underlying media type. Implementations conforming to this specification MAY
support Content-Encodings other than identity. Note that since DM messages are frequently used in bandwidth constrained
environments, the use of compression may be especially desirable. The following is an example of how this header is
specified to indicate that gzip compression was used on the message.

	Content-Encoding: gzip

The Transfer-Encoding general header is used to indicate what (if any) type of transformation has been applied to the
message body in order to safely transfer it between the sender and the recipient. Implementations conforming to this
specification MUST support the chunked Transfer-Encoding option.

	Transfer-Encoding: chunked

The other general headers MAY be supported by implementations conforming to this specification.

####Request Headers

The following table summarizes the support for the HTTP request headers in the DM HTTP binding.

Request Header
Accept
Accept-Charset
Accept-Encoding
Accept-Language
Authorization
Client Requirements
MUST
MUST
MAY
MAY
MAY

Server Requirements
MUST
MUST
MAY
MAY
MAY
nding-V1_3-20120306-C
Expect
From
Host
If-Match
If-Modified-Since
If-None-Match
If-Range
If-Unmodified-Since
Max-Forwards
Proxy-Authorization
Range
Referer
TE
User-Agent
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MUST
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MUST

The Accept request header is used to specify which media types are acceptable in the response.

The following is an example of how this header is specified to indicate that the client expects responses formatted according
to the binary, WBXML representation of device management

	Accept: application/vnd.syncml.dm+wbxml

The Accept-Charset request header is used to specify which character sets are acceptable in the response. DM Servers MUST
support this header with the "UTF-8" character set value. DM Clients SHOULD support the "UTF-8" character set. DM
Clients MAY support additional, IANA registered character set values. DM Client implementations not supporting UTF-8
SHOULD take careful consideration of the potential impact of lack of UTF-8 support on interoperability of the device. If a
recipient is unable to provide support for the character set encoding specified in the Accept-Charset request headers sent by
the originator, the recipient MUST send to the originator a HTTP status 406, "Not acceptable". This is in keeping with
[RFC2616]. Note that there will be no DM Message sent with this response. The following is an example of how this header is
specified to indicate that the client expects responses formatted into the UTF-8 character set:

	Accept-Charset: UTF-8

The Authorization request header is used by an HTTP client to authenticate itself to the HTTP server. DM Clients and DM
Servers MAY support this header with the authorization values for "Digest Access Authentication" authentication schemes,
as specified in [RFC2617]. The following is an example of how this header is specified to allow the HTTP client to
authenticate itself with a Base64 character encoding of a userid of Aladdin and password of open sesame.

	Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==

The Accept-Encoding request header is similar to Accept, but restricts the content-codings (Section 3.5 of [RFC2616]) that are
acceptable in the response. DM Clients and DM Servers MAY support this header. Note that since DM messages are
frequently used in bandwidth constrained environments, the use of compression may be especially desirable. The following
is an example of how this header is specified to allow the HTTP client to request particular compression types.

	Accept-Encoding: gzip,deflate

The Proxy-Authorization request header is similar to the Authorization header except that it is specified by the HTTP client
and used only by the next proxy in the connection chain. DM Clients and DM Servers MAY support this header and with the
authorization values for "Digest Access Authentication" authentication schemes, as specified in [RFC2617]. The following is
an example of how this header is specified to allow the HTTP client to authenticate itself with a userid of Aladdin and
password of open sesame.

	Proxy-Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==

The User-Agent request header identifies the type of user agent originating the request. This information is useful for the
HTTP server to provide automated recognition of user agents for the sake of tailoring responses to avoid particular
limitations or to take advantage of special features in the HTTP client. Implementations conforming to this specification
MUST support this header and provide it in all HTTP requests. The following is an example of the usage of this header.

	User-Agent: Foo Bar DM Products v3.4

The other request headers MAY be supported by implementations conforming to this specification.

####Response Headers

The following table summarizes the support for the HTTP response headers in the DM HTTP binding.

Method
Accept-Ranges
Age
Allow
Authentication-Info
Etag
Location
Proxy-Authenticate
Retry-After
Server
Vary
WWW-Authenticate
Client Requirements
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
Server Requirements
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY
MAY

The Authentication-Info response header is defined by [RFC2617]. The header is used by an HTTP proxy or server to provide
information back to the HTTP client about a successful HTTP client authentication. Implementations conforming to this
specification MAY support this header with the Authentication-Information directives "nextnonce" and "rspauth". The former
directive is used to specify the nonce to be used by the client for a future authentication. The nonce value SHOULD be a
Base64 formatted string. The nextnonce value string MUST be 64 octets or less in length. The latter directive is used by the
HTTP proxy or server to authenticate itself to the HTTP client. The following example shows how an HTTP server might use
this response header to provide authentication credentials to an HTTP client that has successfully authenticated itself with the
HTTP server.

	Authentication-Info: nextnonce="Bruce"
	rspauth="edd30630e82fabdc1e895d1d3a4c0450"

The Proxy-Authenticate response header is used by an HTTP proxy to challenge the authority of the HTTP client issuing it an
HTTP request. Implementations conforming to this specification MAY support this header with the challenge values for
"Basic" and "Digest Access Authentication" authentication schemes and the "Realm", "Domain", "Nonce", "Stale" and
"Algorithm" authentication parameters, as specified in [RFC2617]. The nonce value SHOULD be a Base64 formatted string.
The "MD5" algorithm MAY be supported by implementations that conform to this specification. Other algorithms can also
be supported. The following is an example of this header being used by an HTTP proxy to challenge a HTTP client with the
Digest authentication scheme for the http://www.dm.host.com realm.

	Proxy-Authenticate: Digest
	Domain="http://www.devicemgmt.org/servlet/dm"

The WWW-Authenticate response header is used by the HTTP server to challenge the authority of the HTTP client issuing it
an HTTP request. Implementations conforming to this specification MAY support this header with the challenge values for
"Basic" and "Digest Access Authentication" authentication schemes and the "Realm", "Domain", "Nonce", "Stale" and
"Algorithm" authentication parameters, as specified in [RFC2617]. The nonce value SHOULD be a Base64 formatted string.
The "MD5" algorithm MAY be supported by implementations that conform to this specification. Other algorithms can also
be supported. The following is an example of this header being used by an HTTP server to challenge an HTTP client with the
Basic authentication scheme for the WallyWord@dm.host.com realm.
	
	WWW-Authenticate: Basic Realm="WallyWorld@dm.host.com"

The other response headers MAY be supported by implementations conforming to this specification.

####Pushing Data from the server to the client

See the Push Binding [DMPush] for information on how to push a DM Message to the DM Client.

###Security

HTTP client and HTTP server authentication is based on the mechanism defined in [RFC2617]. Implementations conforming
to this implementation MUST support this mechanism for "Basic" and "Digest Access Authentication". The Base64 character
encoded "Basic" and "MD5" algorithm of the "Digest Access Authentication" authentication schemes MAY be supported.
The HTTP headers and parameters that MUST be supported are described in the previous sections for request and response
headers.

The Content-MD5 header field can be used to provide a message integrity check of the DM Message in the body. Use of this
header is a good idea for detecting accidental modification of the entity-body in transit, but is not proof against malicious
attack.

HTTP proxy and HTTP server implementations conforming to this specification MAY support both the ability to challenge
unauthenticated requests and also accept authentication request headers in a request; which will not require subsequent
challenge responses unless some part of the credential is incorrect. The latter requirement is REQUIRED to address the need
for minimal request/response traffic for mobile networks.

The authentication mechanisms defined by [RFC2617] address protecting the authentication credentials. However, the
remainder of the HTTP request and response messages are available to the eavesdropper. For more robust security for the
HTTP connection, SSL [SSL], TLS 1.1 [RFC4346], TLS 1.2 [RFC5246], PSK-TLS [RFC4279], HTTPS, or some form of
upgrading to TLS over HTTP [RFC2817] [RFC2818] SHOULD be used.

When operating over HTTP:

* The Server and Client MUST support TLS 1.1[RFC4346].
* The Server and Client MUST use TLS 1.0 [RFC4279] or SSL3.0 [SSL] or TLS 1.1 [RFC4346] or PSK-TLS [RFC4279]
   or TLS 1.2 [RFC5246].
* The Server SHOULD support TLS 1.0 [RFC4279] or TLS 1.2 [RFC5246], SSL 3.0 [SSL] and PSK-TLS [RFC4279].
* The Client MUST identify that the Server is using TLS1.0 [RFC4279] or SSL3.0 [SSL] or TLS 1.1 [RFC4346] or
   PSK-TLS [RFC4279] or TLS 1.2 [RFC5246].
* A Session SHALL NOT take place over SSL2.0 or less.
* The Server MUST support both of the following cipher suites, both of which provide authentication, confidentiality
   and integrity, when using an SSL3.0 session

	* SSL_RSA_WITH_RC4_128_SHA
	* SSL_RSA_WITH_3DES_EDE_CBC_SHA

* The Client MUST support at least one of the following cipher suites, both of which provide authentication,
confidentiality and integrity, when using an SSL3.0 session

	* SSL_RSA_WITH_RC4_128_SHA
	* SSL_RSA_WITH_3DES_EDE_CBC_SHA

* The Server MUST support both of the following cipher suites, both of which provide authentication, confidentiality
and integrity, when using an PSK-TLS session

	* TLS_PSK_WITH_AES_128_GCM_SHA256
	* TLS_RSA_PSK_WITH_AES_128_GCM_SHA256
	* TLS_PSK_WITH_AES_128_CBC_SHA256
	* TLS_DHE_PSK_WITH_AES_128_CBC_SHA256
	* TLS_DHE_PSK_WITH_AES_128_GCM_SHA256
	* TLS_RSA_PSK_WITH_AES_128_CBC_SHA256

* The Client MUST support at least one of the following cipher suites, both of which provide authentication,
confidentiality and integrity, when using an PSK-TLS session

	* TLS_PSK_WITH_AES_128_GCM_SHA256
	* TLS_DHE_PSK_WITH_AES_128_GCM_SHA256
	* TLS_RSA_PSK_WITH_AES_128_GCM_SHA256
	* TLS_PSK_WITH_AES_128_CBC_SHA256
	* TLS_DHE_PSK_WITH_AES_128_CBC_SHA256
	* TLS_RSA_PSK_WITH_AES_128_CBC_SHA256

* The Server MUST only accept the usage of cipher suites with all the following characteristics:

	* At least 128 bit symmetric keys;
	* Provide key exchange;
	* Provide signing;
	* Provide bulk encryption;
	* Provide message authentication.

* The Server MUST support the requirements relating to certificates and certificate processing in section 6.3 and 6.4
   of the WAP TLS Profile and Tunneling, [WAP-219-TLS].
* If the Client supports TLS1.0, it MUST support the requirements relating to certificates and certificate processing in
   section 6.3 and 6.4 of the WAP TLS Profile and Tunneling, [WAP-219-TLS].
