#OMA -TS-DM_BootStrap#

##Introduction##

Other OMA DM specifications define how a management session is established and maintained. However, in order for a DM
Client to be able to initiate a management session, it must be provisioned with OMA DM settings.

Bootstrap is the process of provisioning the DM Client to a state where it is able to initiate a management session to a new
DM Server. DM Clients that have already been bootstrapped can be further bootstrapped to enable the DM Client to initiate a
Management Session to new DM Servers or may be rebootstrapped to update existing accounts.

## Bootstrapping##

###Bootstrap scenarios###

OMA DM Clients need to be able to operate in different network environments and using a large set of protocols. This makes
it hard to find a ‘one size fits all’ solution to the bootstrap problem. This section starts with the most basic requirements for
bootstrap and continues to define three different processes for bootstrap

####Requirements####

An OMA DM solution capable of transforming an empty, clean DM Client into a state where it is able to initiate a
management session needs to address these requirements.
* Re-use technology (WAP Push, HTTP Push)
* Tightly standardized and simple  Highly interoperable
* Self sufficient and complete
* Secure (signed and authenticated)
* Data format should be XML based
* Content mappable to OMA DM management objects
* Transport encoding should be [WBXML1.1], or [WBXML1.2], or [WBXML1.3]

####Solutions####

This document defines the following ways to perform the bootstrap process.
* Customized bootstrap
   
Devices are loaded with OMA DM account and connectivity information at manufacture. Also referred to as factory
  bootstrap.
* Bootstrap from smartcard
   
   The smartcard is inserted in the Device and the DM Client is bootstrapped from the smartcard.
*  Server initiated bootstrap
   
DM Server sends out Bootstrap Message via some push mechanism, e.g. WAP Push or OBEX. DM Server needs to
  receive the Device address/phone number beforehand.
*  Client initiated bootstrap
   
DM Client retrieves the Bootstrap Message from a DM Bootstrap Server, whose URL is known to the Device a
  priori.

The DM Client MUST support at least one of these processes for each of the supported profiles (see section 5.3).

#####Customized bootstrap#####

This is a convenient way to bootstrap a DM Client from an end user perspective because the user does not have to do
anything. In this scenario, an operator orders the Devices pre-configured from a device manufacturer. All the information
about the operator’s network and device management infrastructure is already in the Devices when they leave the factory.
Another advantage of this method is that it is very secure. There is no need to transport sensitive commands and information,
e.g. shared secrets, over the air. The method is however not very flexible and not all device manufacturers may provide this
service. Not all Devices are sourced via the operator. In this scenario, either the DM Server or the DM Client initiates an
OMA DM Management Session after user personalizes and bootstraps the DM Client.

Figure 1 gives an overview of this scenario.

![figure1](figure1.png)

Figure 1: Customized bootstrap

#####Bootstrap from smartcard#####

This is a convenient way to bootstrap a DM Client from an end user perspective because the user does not have to do
anything. In this scenario the DM Client is able to obtain the Bootstrap Message from the smartcard. There is no need to
transport sensitive bootstrap commands and information, e.g. shared secrets, over the air. The smartcard is secure, ensuring
that the Bootstrap Message is authorized. A Device supporting the smartcard can be bootstrapped for DM without necessarily
being purchased from the operator. In this scenario, either the DM Server or the DM Client initiates a Management Session
after DM Client bootstraps.

![figure2](figure2.png)

Figure 2: Bootstrap from smartcard

#####Server initiated bootstrap#####

In this scenario, the Devices leave the assembly line in a clean and empty state. Once a user acquires a Device and
personalizes it, e.g. by inserting a SIM, the prerequisites for this process are in place. The problem is now to inform the DM
Server of the identity, address or phone number of the device and this can be achieved in many ways.
* It could be done at the point-of-sales where a sales system ties in with the management system and delivers the
   information.
* It could be done through a self-service web site where the user enters her own phone number.
* It could be done by the network the first time the Device registers to the network. When this happens a trigger could
   be sent from the core network to the DM Server with the number used by the Device.
* It could be done with a voice prompt system where the user is prompted to key in her phone number.

Regardless of how the phone number or Device address reaches the DM Server, the DM Server is now in a position where it
can send out a Bootstrap Message. This message, whose structure and content are defined in this document, contains enough
information for the DM Client to be able to initiate a management session with the device management server that sent out
the Bootstrap Message.

The DM Clients SHOULD accept Bootstrap Messages only from authorized servers [DMSecurity].

Figure 3 gives an overview of this scenario.

![figure 3](figure3.png)

Figure 3: Server initiated bootstrap

#####Client initiated bootstrap#####

In this scenario, the DM Client retrieves the bootstrap package from a DM Bootstrap Server, whose URL is known to the
Device a priori, as shown in Figure 4; in Appendix E a method to discovery Bootstrap Server is described. After the bootstrap
package gets installed successfully, the DM Client attempts to initiate a management session with the DM Server.

![figure 4](figure4.png)

Figure 4: Client initiated bootstrap

###OTA Bootstrap Package Delivery (Informative)

OTA (Over-the-air) delivery of the DM bootstrap package is based on OMA Push
[PushOTA][PushOTA][PushOTA][PushOTA][PushOTA]. The policy that the Device consults to decide if a bootstrap package will
be accepted is outside the scope of this specification. One approach is for a Device to support the Push Management Object
[PushMO], which maintains a “White List” of entities that are authorized to send OMA Push messages to the Device. In this
case the Device will process the Push message only if it originates from an authorized entity.

###Bootstrap profiles

OMA DM has been designed to meet the management requirements of many different types of devices. For some of these
device types there already exists a bootstrap or provisioning mechanism. In these cases OMA DM leverages the existing
mechanisms so that backwards compatibility and simple deployment can be achieved. To define how different kinds of
devices can be bootstrapped and to specify how OMA DM leverages existing standards this document introduces the concept
of bootstrap profiles. Each profile defines its own security, transport and data format.
Currently two profiles are planned, but as interest in OMA DM grows and usage of it increases more profiles can be added.
The Device Management Profile MUST be supported. Any other particular profile MAY be supported.

** OMA Client Provisioning **

This profile specifies alignment of two existing enablers – OMA Client Provisioning [ERELDCP] and OMA Device
Management [ERELDDM]. This profile defines how the information provisioned using OMA Client Provisioning can be
transferred to the management tree specified in the OMA Device Management. In this profile at least the mapping of w7
(DM account) information to the management tree needs to be supported, but other provisioning information can also be
mapped to the management tree.

** OMA Device Management **

This profile defines how the OMA Device Management [ERELDDM] can be used for bootstrapping.

##OMA Client Provisioning Profile

OMA Client Provisioning enabler [ERELDCP] is designed to provision the initial configuration information to Devices, and
can be used to enable a device to be managed by OMA Device Management enabler [ERELDDM]. The chapter specifies the
mapping of the Client Provisioning information to the Device’s management tree in a way that later management for the
provisioned parameters is possible in case both Client Provisioning and Device Management enablers are supported by the
Device.

The content of the provisioning message is based on the OMA Provisioning Content Specification [PROVCONT]. In order to
enable the usage of the OMA Provisioning Content Specification within the OMA Device Management framework, the DM
application registration document w7 [ACw7DM] is released by DM group to provide information how the APPLICATION
characteristic in OMA Provisioning content [PROVCONT] is used to provision OMA Device Management enabler
[ERELDDM] parameters.

###Transports

Bootstrapping using OMA Client Provisioning profile is done as defined in the OMA Client Provisioning Bootstrap
specification [PROVBOOT].

###Mapping Characteristic Data to the Management Tree

When Device receives Client Provisioning document, the DM Client creates a management object for each application
characteristic in the DM management tree. Management object can have two different types of name space identifiers
(Property Name described in [DMTND]) - One where the name is already given in the DDF [DMTND] and another where the
name is dynamic separating the instances of the child nodes (see Figure 5).

![figure5](figure5.png)

Figure 5: Example Management Object and Name Identifiers

The name identifiers for named nodes are already given in the management object DDF. Also, the parameter mapping
between Client Provisioning parameters and Management Object parameters MAY be specified in the Management Object
specification. In addition a general rule that SHOULD be followed to map named information between Client Provisioning
APPLICATION characteristic and standardized Connectivity Management Object template structure is given in Appendix C.

The DM Client gives the name identifiers for dynamic nodes that are separating the instances of the child nodes. Though the
format of name identifiers for these dynamic nodes is implementation specific, a client MAY assign numeric identifiers
starting from ‘1’ and increasing by one every time. In this case and when there is priority specified in the Provisioning
Content document the rank SHOULD reflect that.

####Management Object Location in the Management Tree

Newly created management object location in the management tree is decided by the DM Client. However, it MUST be
placed following the published DDF of the management tree so that the server is able to know where to find the provisioned
information.

####Management Object Access Rights

All provisioning information mapped from the Provisioning Content [PROVCONT] document to management tree MUST be
granted Get, Replace and Delete ACL rights to the ServerID specified in the w7 APPLICATION characteristic provisioned
inside Provisioning Content message. The management authority owning the ServerID may modify this ACL in a
subsequent DM session.
In case w7 APPLICATION characteristic is not part of the provisioning message the Device receiving the message and
mapping the information to the DM management tree MUST NOT give the access rights to these parameters to the improper
management authority.

####Special Behaviors - Smart Card Provisioning

In case Smart Card contains the provisioning information as specified in the [PROVSC], the Device SHOULD detect the
removal and/or change of the Smart Card. When the Smart Card is removed and/or changed, the DM Client SHOULD
remove all the provisioned management object information (originated from the Smartcard) from the DM management tree.

####Device Management, Access Point and Proxy Information

Devices supporting both Client Provisioning and Device Management MUST be able to map w7 (Device Management
account) and NAPDEF (if supported) and PROXY (if supported) characteristics information to the DM management tree.
The mapping of the named nodes is specified in [DMSTDOBJ]. An explicit mapping of w7 to DMAcc is provided in
Appendix C of [DMSTDOBJ], a general mapping of application characteristics can be found in Appendix C of this document.
The DM Client MUST give the names for the dynamic nodes as described in Section 5.4.2.

####Other Client Provisioning information

Devices supporting both Client Provisioning and Device Management MAY decide to map other information provisioned in
the Client Provisioning message to the DM management tree. In case a specific mechanism is described in the Management
Object document that mapping MUST be followed.

###OMA Device Management Profile

The OMA DM Bootstrap Profile includes procedures by which a DM Client installs or updates the DMAcc [DMSTDOBJ]
MO, upon receiving a TNDS [DMTNDS] encoded Bootstrap Message. Additionally other MOs may also be installed or
updated.
The content of the Bootstrap Message is a standard OMA DM message. DM Clients MUST support embedded WBXML
encoded TNDS objects and normal TNDS objects and MUST support the Inbox. In order to be bootstrapped successfully, the
DM client requires both DM account information and connectivity information. It is RECOMMENDED to use standardized
connectivity MOs to represent the connectivity information.

####Transport

See the security document for transport and security information [DMSecurity].

####Management tree ACL and bootstrap

The policy that the Device consults to decide if a Bootstrap Message will be accepted is outside the scope of this
specification. If a Bootstrap Message is accepted it MUST be processed according to the conditions described in section
"Processing of the Bootstrap".

####Management Object Access Rights

When a Bootstrap Message adds new TNDS objects, any ACL values that are to be set for these objects MUST be included
in the TNDS data as ACL property data for the applicable nodes.

####Bootstrap Message Content

The content of a Bootstrap Message is a normal DM Message. However, it is a special package in many ways since it is not
part of an ongoing OMA DM session but rather a one-time message. Hence, many of the elements needed to manage the
session are superfluous in this context, but they must still be included so that the message may be processed by the normal
DM Client.
A Bootstrap Message MUST set the values for the DMAcc management object defined in [DMSTDOBJ]. Other values (such
as connectivity settings) MAY also be set.
The value of the SyncHDR/Source/LocURI element for a Bootstrap Message MUST match the value of one of the
AppAddr/<x>/Addr nodes of a DMAcc object in that Bootstrap Message.
DM Bootstrap Message MUST be [WBXML1.1], or [WBXML1.2], or [WBXML1.3] encoded.
DM Servers MUST NOT expect any response message for a Bootstrap Message. An implicit acknowledgement of successful
processing of a Bootstrap Message can be concluded when the client connects to the server for the first Management Session.
See the DM Security document [DMSecurity] for information on security and encryption.

####Processing of the Bootstrap

A Bootstrap Message is processed just like a normal DM Message, except that a response message MUST NOT be sent back.

The DM Client MAY rename a new MO. In the case of the Connectivity MO the DM Client SHOULD also rename the
values of the corresponding connectivity references to the new name for all MO’s encoded within the same TNDS object.

When a TNDS object contains a MO where connectivity references are linked to a Connectivity or Proxy MO that also are
included in the same TNDS object, then the values of those connectivity references MAY contain a URI that starts with
“./Inbox”. In that case the URI MUST have the value of “./Inbox/” plus the URI of that Connectivity MO’s location in the
same TNDS object.

This is an example of a TNDS object where only part of the TNDS object is shown:

    <MgmtTree>
        <VerDTD>1.2</VerDTD>
        <Node>
            <NodeName>OperatorX</NodeName><!-- DM Account MO -->
            <RTProperties>
                <Format>
                    <node/>
                </Format>
                <Type><DDFName>org.openmobilealliance/1.0/w7</DDFName></Type>
            </RTProperties>
        <Node>
            <NodeName>PrefConRef</NodeName>
            <RTProperties>
                <Format>
                    <chr/>
                </Format>
                <Type><MIME>text/plain</MIME></Type>
            </RTProperties>
            <Value>./Inbox/Internet</Value>
         </Node>
         ...
         <NodeName>Internet</NodeName><!-- Connectivity MO -->
         <RTProperties>
             <Format>
                 <node/>
             </Format>
             <Type><DDFName>org.openmobilealliance/1.0/ConnMO</DDFName></Type>
         </RTProperties>
          ...
        </Node>
    </MgmtTree>

If a DM Client encounters an item with an URI of the EXT sub-tree that it is not prepared to handle, the DM Client MAY
ignore that item so that the message may succeed.

After successfully processing the Bootstrap Message, the DM Client SHOULD automatically initiate a Management Session
to any DM Server configured in the Bootstrap Message at the next practical opportunity, subject to restrictions and
configuration in the DMAcc of each bootstrapped server (i.e., when network connectivity and other factors would allow such
a connection).

If the Bootstrap Message contains a MO that the DM Client does not support, the DM Client MAY ignore this MO, so that
the message may succeed.

If the Bootstrap Message contains multiple versions of a MO, the DM Client SHOULD use the latest version of that MO that
it supports and ignore the other versions, so that the message may succeed.

###Smartcard

If the Device supports a smartcard, the DM Client MUST support detection, retrieval, and processing of Bootstrap Message
from the smartcard as described in Appendix D. The DM Client MAY include configurable security policy to disable
smartcard bootstrap functions. If the smartcard bootstrap function is enabled (i.e. no security policy is implemented or
security policy does not disable smartcard bootstrap) and the smartcard has not been rejected by the device (for example,
because of a SIM-locking mechanism), the DM Client SHALL retrieve the Bootstrap Message from the smartcard when the
device is switched on and apply it to the device configuration.

The DM Client SHOULD check that the bootstrap data for all DM Servers previously bootstrapped from the smartcard are
still available from the smartcard when the device is switched on; if not, the information for any DM Servers that were
previously bootstrapped from the smartcard but are no longer stored on the smartcard SHOULD be removed from the Device
Management tree.

####Bootstrap via HTTPS Get

If the Device supports the HTTPS protocol, it MAY retrieve a Bootstrap Message from a URL by following these steps:
1.  The device performs a HTTPS Get to a Bootstrap Server.
2.  The Bootstrap returns the Bootstrap Message to the Device or indicates the Bootstrap Message is not available (e.g.
  returns error code 404).
3.  If the Bootstrap Message is returned to the device, it is handed off to the DM Client.
4.  Upon successful verification of the Bootstrap Message, the DM Client processes the Bootstrap Message as normal.

If the Device supports HTTPS protocol and [SCWS], it MAY retrieve a bootstrap message by following the above steps and
using the following absolute URL: “https://{SCWS@}/OMA/DM/Bootstrap.xml”, where {SCWS@} depends on the
transport and IP version supported as shown in the following table:

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Transport
        </th>
        <th>
            IP version
        </th>
        <th>
            {SCWS@}
        </th>
    </tr>
    <tr>
        <td rowspan="2">
            BIP<br/>
            (Note 1)
        </td>
        <td>
            IPv4 
        </td>
        <td>
            127.0.0.1:4116
        </td>
    </tr>
    <tr>
        <td>IPv6</td>
        <td>[::1]:4116</td>
    </tr>
    <tr>
        <td rowspan="2">
            TCP/IP
        </td>
        <td>
            IPv4 
        </td>
        <td>
            localuicc:443
        </td>
    </tr>
    <tr>
        <td>IPv6</td>
        <td>localuicc:443</td>
    </tr>
</table>
(Note 1) The Device MAY use “localhost” host name instead of loopback address “127.0.0.1” for IPv4 or “[::1]” for IPv6.

##Bootstrap Config MO

###Introduction

The Bootstrap Config MO provides the ability to manage the bootstrap functionality on a Device. This MO MUST NOT be
used to manage the initial access rights of the DM Server unless the DM Client is bootstrapped to at least one other DM
Server.

Support for this MO is OPTIONAL.

###Graphical Representation (Informative)

Figure 6 gives the graphical representation of the Bootstrap Config MO.

![figure6](figure6.png)

Figure 6: Bootstrap Config MO

###Node Descriptions

This section provides the description of the various nodes within the Bootstrap Config MO.
.../&lt;x&gt;

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Status 
        </th>
        <th>
            Occurrence 
        </th>
        <th>
            Format 
        </th>
        <th>
            Min. Access Types
        </th>
    </tr>
    <tr>
        <td>Optional</td>
        <td>ZeroOrOne</td>
        <td>node</td>
        <td>Get</td>
    </tr>
</table>

This placeholder node is the root node for the Bootstrap Config MO. The parent node of this node defines the
location of this MO in the Management Tree.

The Management Object Identifier for the Bootstrap Config MO MUST be: “urn:oma:mo:oma-dm-
bootstrapcfg:1.0”.

&lt;x&gt;/BootSrvDiscovery

 <table class="table table-bordered table-straped">
    <tr>
        <th>
            Status 
        </th>
        <th>
            Occurrence 
        </th>
        <th>
            Format 
        </th>
        <th>
            Min. Access Types
        </th>
    </tr>
    <tr>
        <td>Required</td>
        <td>One</td>
        <td>int</td>
        <td>Get, Replace</td>
    </tr>
</table>   

This leaf node indicates whether or not the Device is allowed to discover a DM Bootstrap Server. The permitted
values for this node are shown in the following table.
<table class="table table-bordered">
    <tr>
        <td>0</td>
        <td>
             The Device is inhibited from discovering the DM Bootstrap
 Server.
        </td>
    </tr>
    <tr>
        <td>1</td>
        <td>
             The Device is allowed to discover the DM Bootstrap
 Server.
        </td>
    </tr>
</table>
The default value for this node is left to implementations.

&lt;x&gt;/BootSrvInfo

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Status 
        </th>
        <th>
            Occurrence 
        </th>
        <th>
            Format 
        </th>
        <th>
            Min. Access Types
        </th>
    </tr>
    <tr>
        <td>Required</td>
        <td>ZeroOrOne</td>
        <td>node</td>
        <td>Get</td>
    </tr>
</table> 

This interior node is the parent node of the subtree that stores the DM Bootstrap Server URLs, along with the
pertinent credential and access rights information.

&gt;x&lt;/BootSrvInfo/&gt;x&lt;

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Status 
        </th>
        <th>
            Occurrence 
        </th>
        <th>
            Format 
        </th>
        <th>
            Min. Access Types
        </th>
    </tr>
    <tr>
        <td>Required</td>
        <td>ZeroOrOne</td>
        <td>node</td>
        <td>Get</td>
    </tr>
</table> 

This placeholder node is the root node for all the information pertaining to one DM Bootstrap Server.

&lt;x&gt;/BootSrvInfo/&lt;x&gt;/URL

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Status 
        </th>
        <th>
            Occurrence 
        </th>
        <th>
            Format 
        </th>
        <th>
            Min. Access Types
        </th>
    </tr>
    <tr>
        <td>Required</td>
        <td>One</td>
        <td>chr</td>
        <td>Get, Replace</td>
    </tr>
</table> 

The value of this leaf node is the URL of a DM Bootstrap Server.

&lt;x&gt;/BootSrvInfo/&lt;x&gt;/Ext

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Status 
        </th>
        <th>
            Occurrence 
        </th>
        <th>
            Format 
        </th>
        <th>
            Min. Access Types
        </th>
    </tr>
    <tr>
        <td>Optional</td>
        <td>ZeroOrOne</td>
        <td>node</td>
        <td>Get</td>
    </tr>
</table> 

This interior node is for vendor specific extensions for managing DM Bootstrap Server URLs on the Device.

&lt;x&gt;/InitialARInfo

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Status 
        </th>
        <th>
            Occurrence 
        </th>
        <th>
            Format 
        </th>
        <th>
            Min. Access Types
        </th>
    </tr>
    <tr>
        <td>Required</td>
        <td>ZeroOrOne</td>
        <td>node</td>
        <td>Get</td>
    </tr>
</table> 

This interior node is the root node for all the initial access rights information. If this node is not present, the initial
ACL access rights are assumed to be as per the device policy.

&lt;x&gt;/InitialARInfo/&lt;x&gt;

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Status 
        </th>
        <th>
            Occurrence 
        </th>
        <th>
            Format 
        </th>
        <th>
            Min. Access Types
        </th>
    </tr>
    <tr>
        <td>Required</td>
        <td>OneOrMore</td>
        <td>node</td>
        <td>Get, Add, Delete</td>
    </tr>
</table> 

This placeholder node is the root node for the initial access rights information for one subtree within the
Management Tree.

&lt;x&gt;/InitialARInfo/&lt;x&gt;/SubtreeURI

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Status 
        </th>
        <th>
            Occurrence 
        </th>
        <th>
            Format 
        </th>
        <th>
            Min. Access Types
        </th>
    </tr>
    <tr>
        <td>Required</td>
        <td>One</td>
        <td>chr</td>
        <td>Get, Replace</td>
    </tr>
</table>

This value of this leaf node is the URI of the root of a subtree within the Management Tree.

&lt;x&gt;/InitialARInfo/&lt;x&gt;/AccessCode

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Status 
        </th>
        <th>
            Occurrence 
        </th>
        <th>
            Format 
        </th>
        <th>
            Min. Access Types
        </th>
    </tr>
    <tr>
        <td>Required</td>
        <td>One</td>
        <td>int</td>
        <td>Get, Replace</td>
    </tr>
</table>

This node specifies initial access rights that the DM Server is granted on the subtree, whose root is specified by the
value of SubtreeURI node, upon successful completion of the Bootstrap procedure. The valid value of this node is
any Access Type value from the following table, or any value obtained from the bit-wise ORing of the Access Type
values:

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Access Type
        </th>
        <th>
             Value
        </th>
    </tr>
    <tr>
        <td>
            Get
        </td>
        <td>
             1 (i.e. 0x1)
        </td>
    </tr>
    <tr>
        <td>
            Replace
        </td>
        <td>
             2 (i.e. 0x2)
        </td>
    </tr>
    <tr>
        <td>
            Exec
        </td>
        <td>
             4 (i.e. 0x4)
        </td>
    </tr>
    <tr>
        <td>
            Copy
        </td>
        <td>
             8 (i.e. 0x8)
        </td>
    </tr>
    <tr>
        <td>
            Add
        </td>
        <td>
             16 (i.e. 0x10)
        </td>
    </tr>
    <tr>
        <td>
            Delete
        </td>
        <td>
             32 (i.e. 0x20)
        </td>
    </tr>
</table>

For example, if the ACL rights are only Get, the value of this node is 1. If the ACL rights are Get, Add and Delete,
the value of this node is 49.

If the value of the SubtreeURI node does not correspond to a node in the Management Tree then the value of this
node MUST be ignored.

&lt;x&gt;/Ext

<table class="table table-bordered table-straped">
    <tr>
        <th>
            Status 
        </th>
        <th>
            Occurrence 
        </th>
        <th>
            Format 
        </th>
        <th>
            Min. Access Types
        </th>
    </tr>
    <tr>
        <td>Optional</td>
        <td>ZeroOrOne</td>
        <td>node</td>
        <td>Get</td>
    </tr>
</table>

This interior node is for vendor specific extensions for managing the client initiated bootstrap functionality on a
Device.
