import XmlUtils.XmlDataUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SCTP_Server {

    static String init_ack_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
            "<sctp>\r\n" +
            "					<sctp.srcport></sctp.srcport>\r\n" +
            "					<sctp.dstport></sctp.dstport>\r\n" +
            "					<sctp.verification_tag>0x71b81d1f</sctp.verification_tag>\r\n" +
            "					<sctp.assoc_index>0</sctp.assoc_index>\r\n" +
            "					<sctp.port></sctp.port>\r\n" +
            "					<sctp.checksum>0xf68a32a1</sctp.checksum>\r\n" +
            "					<sctp.checksum.status>2</sctp.checksum.status>\r\n" +
            "					<INIT_ACK>\r\n" +
            "						<sctp.chunk_type>2</sctp.chunk_type>\r\n" +
            "						<sctp.chunk_type_tree>\r\n" +
            "							<sctp.chunk_bit_1>0</sctp.chunk_bit_1>\r\n" +
            "							<sctp.chunk_bit_2>0</sctp.chunk_bit_2>\r\n" +
            "						</sctp.chunk_type_tree>\r\n" +
            "						<sctp.chunk_flags>0x00000000</sctp.chunk_flags>\r\n" +
            "						<sctp.chunk_length>192</sctp.chunk_length>\r\n" +
            "						<sctp.initack_initiate_tag>0x48e63127</sctp.initack_initiate_tag>\r\n" +
            "						<sctp.initiate_tag>0x48e63127</sctp.initiate_tag>\r\n" +
            "						<sctp.initack_credit>109568</sctp.initack_credit>\r\n" +
            "						<sctp.initack_nr_out_streams>10</sctp.initack_nr_out_streams>\r\n" +
            "						<sctp.initack_nr_in_streams>10</sctp.initack_nr_in_streams>\r\n" +
            "						<sctp.initack_initial_tsn>4194126429</sctp.initack_initial_tsn>\r\n" +
            "						<State-cookie>\r\n" +
            "							<sctp.parameter_type>0x00000007</sctp.parameter_type>\r\n" +
            "							<sctp.parameter_type_tree>\r\n" +
            "								<sctp.parameter_bit_1>0</sctp.parameter_bit_1>\r\n" +
            "								<sctp.parameter_bit_2>0</sctp.parameter_bit_2>\r\n" +
            "							</sctp.parameter_type_tree>\r\n" +
            "							<sctp.parameter_length>164</sctp.parameter_length>\r\n" +
            "							<sctp.parameter_state_cookie>41:f6:c2:fe:87:3a:b1:64:60:4d:b8:52:4d:8a:33:d0:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:27:31:e6:48:1f:1d:b8:71:00:00:00:00:00:00:00:00:c5:31:da:41:1b:f6:03:00:0a:00:0a:00:5d:4a:fd:f9:02:00:0a:1a:c0:a8:00:65:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:01:00:00:00:00:00:00:00:01:00:00:24:71:b8:1d:1f:00:01:ac:00:00:0a:ff:ff:a1:10:4d:8a:00:0c:00:06:00:05:00:00:80:00:00:04:c0:00:00:04:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00</sctp.parameter_state_cookie>\r\n" +
            "						</State-cookie>\r\n" +
            "						<ECN-parameter>\r\n" +
            "							<sctp.parameter_type>0x00008000</sctp.parameter_type>\r\n" +
            "							<sctp.parameter_type_tree>\r\n" +
            "								<sctp.parameter_bit_1>1</sctp.parameter_bit_1>\r\n" +
            "								<sctp.parameter_bit_2>0</sctp.parameter_bit_2>\r\n" +
            "							</sctp.parameter_type_tree>\r\n" +
            "							<sctp.parameter_length>4</sctp.parameter_length>\r\n" +
            "						</ECN-parameter>\r\n" +
            "						<Forward-TSN-supported-parameter>\r\n" +
            "							<sctp.parameter_type>0x0000c000</sctp.parameter_type>\r\n" +
            "							<sctp.parameter_type_tree>\r\n" +
            "								<sctp.parameter_bit_1>1</sctp.parameter_bit_1>\r\n" +
            "								<sctp.parameter_bit_2>1</sctp.parameter_bit_2>\r\n" +
            "							</sctp.parameter_type_tree>\r\n" +
            "							<sctp.parameter_length>4</sctp.parameter_length>\r\n" +
            "						</Forward-TSN-supported-parameter>\r\n" +
            "					</INIT_ACK>\r\n" +
            "				</sctp>";

    static String cookie_ack_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
            "<sctp>\r\n" +
            "					<sctp.srcport>9999</sctp.srcport>\r\n" +
            "					<sctp.dstport>6666</sctp.dstport>\r\n" +
            "					<sctp.verification_tag>0x71b81d1f</sctp.verification_tag>\r\n" +
            "					<sctp.assoc_index>0</sctp.assoc_index>\r\n" +
            "					<sctp.port>6666</sctp.port>\r\n" +
            "					<sctp.checksum>0x4fb914a0</sctp.checksum>\r\n" +
            "					<sctp.checksum.status>2</sctp.checksum.status>\r\n" +
            "					<COOKIE_ACK>\r\n" +
            "						<sctp.chunk_type>11</sctp.chunk_type>\r\n" +
            "						<sctp.chunk_type_tree>\r\n" +
            "							<sctp.chunk_bit_1>0</sctp.chunk_bit_1>\r\n" +
            "							<sctp.chunk_bit_2>0</sctp.chunk_bit_2>\r\n" +
            "						</sctp.chunk_type_tree>\r\n" +
            "						<sctp.chunk_flags>0x00000000</sctp.chunk_flags>\r\n" +
            "						<sctp.chunk_length>4</sctp.chunk_length>\r\n" +
            "					</COOKIE_ACK>\r\n" +
            "				</sctp>";

    static String sack_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
            "<sctp>\r\n" +
            "					<sctp.srcport>9999</sctp.srcport>\r\n" +
            "					<sctp.dstport>6666</sctp.dstport>\r\n" +
            "					<sctp.verification_tag>0x71b81d1f</sctp.verification_tag>\r\n" +
            "					<sctp.assoc_index>0</sctp.assoc_index>\r\n" +
            "					<sctp.port>6666</sctp.port>\r\n" +
            "					<sctp.checksum>0x8d7eaf46</sctp.checksum>\r\n" +
            "					<sctp.checksum.status>2</sctp.checksum.status>\r\n" +
            "					<SACK>\r\n" +
            "						<sctp.chunk_type>3</sctp.chunk_type>\r\n" +
            "						<sctp.chunk_type_tree>\r\n" +
            "							<sctp.chunk_bit_1>0</sctp.chunk_bit_1>\r\n" +
            "							<sctp.chunk_bit_2>0</sctp.chunk_bit_2>\r\n" +
            "						</sctp.chunk_type_tree>\r\n" +
            "						<sctp.chunk_flags>0x00000000</sctp.chunk_flags>\r\n" +
            "						<sctp.chunk_flags_tree>\r\n" +
            "							<sctp.sack_nounce_sum>0</sctp.sack_nounce_sum>\r\n" +
            "						</sctp.chunk_flags_tree>\r\n" +
            "						<sctp.chunk_length>16</sctp.chunk_length>\r\n" +
            "						<sctp.sack_cumulative_tsn_ack>2702200202</sctp.sack_cumulative_tsn_ack>\r\n" +
            "						<sctp.sack_cumulative_tsn_ack_tree>\r\n" +
            "							<sctp.ack>2702200202</sctp.ack>\r\n" +
            "							<sctp.ack_tree>\r\n" +
            "								<sctp.ack_frame>5</sctp.ack_frame>\r\n" +
            "								<sctp.sack_rtt>0.000151000</sctp.sack_rtt>\r\n" +
            "							</sctp.ack_tree>\r\n" +
            "						</sctp.sack_cumulative_tsn_ack_tree>\r\n" +
            "						<sctp.sack_a_rwnd>109542</sctp.sack_a_rwnd>\r\n" +
            "						<sctp.sack_number_of_gap_blocks>0</sctp.sack_number_of_gap_blocks>\r\n" +
            "						<sctp.sack_number_of_duplicated_tsns>0</sctp.sack_number_of_duplicated_tsns>\r\n" +
            "					</SACK>\r\n" +
            "				</sctp>";

    static String shutdown_ack_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
            "<sctp>\r\n" +
            "					<sctp.srcport>6666</sctp.srcport>\r\n" +
            "					<sctp.dstport>9999</sctp.dstport>\r\n" +
            "					<sctp.verification_tag>0x48e63127</sctp.verification_tag>\r\n" +
            "					<sctp.assoc_index>0</sctp.assoc_index>\r\n" +
            "					<sctp.port>9999</sctp.port>\r\n" +
            "					<sctp.checksum>0xa5c23921</sctp.checksum>\r\n" +
            "					<sctp.checksum.status>2</sctp.checksum.status>\r\n" +
            "					<SHUTDOWN_ACK>\r\n" +
            "						<sctp.chunk_type>8</sctp.chunk_type>\r\n" +
            "						<sctp.chunk_type_tree>\r\n" +
            "							<sctp.chunk_bit_1>0</sctp.chunk_bit_1>\r\n" +
            "							<sctp.chunk_bit_2>0</sctp.chunk_bit_2>\r\n" +
            "						</sctp.chunk_type_tree>\r\n" +
            "						<sctp.chunk_flags>0x00000000</sctp.chunk_flags>\r\n" +
            "						<sctp.chunk_length>4</sctp.chunk_length>\r\n" +
            "					</SHUTDOWN_ACK>\r\n" +
            "				</sctp>\r\n" +
            "";

    public static void main(String args[]) {
        // declaration section:
        // declare a server socket and a client socket for the server
        // declare an input and an output stream
        //System.out.println(XmlDataUtils.insertPeriodically(XmlDataUtils.toHex("Thank you ! Mr. Server ! "), ":", 2) + ", length: " + XmlDataUtils.toHex("Thank you ! Mr. Server ! ").length());
        ServerSocket echoServer = null;
        String Line;
        DataInputStream is;
        DataOutputStream os;
        Socket clientSocket = null;
        // Try to open a server socket on port 25
        // Note that we can't choose a port less than 1023 if we are not
        // privileged users (root)
        int srcPort = 1265;
        int destPort = 66550;
        String verificationTag = "";
        try {
            System.out.println("***********Server***********");
            System.out.println();
            System.out.println("Starting server at port " + srcPort);
            echoServer = new ServerSocket(srcPort);
        } catch (IOException e) {
            System.out.println(e);
        }
        // Create a socket object from the ServerSocket to listen and accept
        // connections.
        // Open input and output streams
        try {
            System.out.println("Waiting for client....");
            clientSocket = echoServer.accept();
            destPort = clientSocket.getPort();
            System.out.println("Client accepted");
            System.out.println("Client port: " + clientSocket.getPort());
            is = new DataInputStream(clientSocket.getInputStream());
            os = new DataOutputStream(clientSocket.getOutputStream());

            // As long as we receive data, echo that data back to the client.

            while ((Line = is.readUTF()) != null) {
                System.out.println();
                System.out.println("********XML Response from Client******");
                System.out.println("--------------------------");
                System.out.println(Line);
                System.out.println("--------------------------");
                System.out.println();
                if (Line.contains("<INIT>")) {

                    String start = "<sctp.verification_tag>";
                    String end = "</sctp.verification_tag>";
                    verificationTag = Line.substring(Line.indexOf(start) + start.length(), Line.indexOf(end));
                    System.out.println(">>>> INIT request received from client with verification tag: " + verificationTag);
                    System.out.println(">>>> Sending INIT_ACK request with verification tag: " + verificationTag);
                    //os.writeUTF(init_ack_xml);
                    os.writeUTF(XmlDataUtils.GetInitAckXml(srcPort, destPort, verificationTag));
                    os.flush();


                } else if (Line.contains("<COOKIE_ECHO>")) {
                    //os.writeUTF(cookie_ack_xml);
                    System.out.println(">>>> Received COOKIE_ECHO from client");
                    System.out.println(">>>> Sending COOKIE_ACK request.....");
                    os.writeUTF(XmlDataUtils.GetCookieAckXml(srcPort, destPort, verificationTag));
                    os.flush();
                } else if (Line.contains("<DATA>")) {
                    //os.writeUTF(sack_xml);
                    System.out.println(">>>> DATA is received from client");
                    String start = "<data.data>";
                    String end = "</data.data>";
                    String msg = Line.substring(Line.indexOf(start) + start.length(), Line.indexOf(end));
                    msg = msg.replaceAll(":", "");
                    System.out.println("**** Client message in DATA:");
                    System.out.println("----------------------------");
                    System.out.println(XmlDataUtils.toString(msg));
                    System.out.println();
                    System.out.println(">>>> Sending SACK request......");
                    os.writeUTF(XmlDataUtils.GetSackAckXml(srcPort, destPort, verificationTag));
                    os.flush();
                } else if (Line.contains("<SHUTDOWN>")) {
                    //os.writeUTF(shutdown_ack_xml);
                    System.out.println(">>>> SHUTDOWN request is received from client");
                    System.out.println(">>>> Sending SHUTDOWN_ACK request.....");
                    os.writeUTF(XmlDataUtils.GetShudownAckXml(srcPort, destPort, verificationTag));
                    os.flush();
                } else if (Line.contains("<SHUTDOWN_COMPLETE>")) {
                    System.out.println(">>>> SHUTDOWN_COMPLETE request is received from client");
//	                    	os.close();
//	                    	is.close();
//	                    	clientSocket.close();
                    break;
                }
                if (Line.indexOf("Ok") != -1) {
                    break;
                }
            }
            System.out.println("********* Closing socket");
            System.out.println("********* Server closed");
            os.close();
            is.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
