import XmlUtils.XmlDataUtils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class SCTP_Client {

    static String init_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
            "\r\n" +
            "<sctp>\r\n" +
            "	<sctp.srcport>6666</sctp.srcport>\r\n" +
            "	<sctp.dstport>9999</sctp.dstport>\r\n" +
            "	<sctp.verification_tag>0x00000000</sctp.verification_tag>\r\n" +
            "	<sctp.assoc_index>0</sctp.assoc_index>\r\n" +
            "	<sctp.port>9999</sctp.port>\r\n" +
            "	<sctp.checksum>0x561ec1fb</sctp.checksum>\r\n" +
            "	<sctp.checksum.status>2</sctp.checksum.status>\r\n" +
            "	<INIT>\r\n" +
            "		<sctp.chunk_type>1</sctp.chunk_type>\r\n" +
            "		<sctp.chunk_type_tree>\r\n" +
            "		<sctp.chunk_bit_1>0</sctp.chunk_bit_1>\r\n" +
            "		<sctp.chunk_bit_2>0</sctp.chunk_bit_2>\r\n" +
            "		</sctp.chunk_type_tree>\r\n" +
            "		<sctp.chunk_flags>0x00000000</sctp.chunk_flags>\r\n" +
            "		<sctp.chunk_length>36</sctp.chunk_length>\r\n" +
            "		<sctp.init_initiate_tag>0x71b81d1f</sctp.init_initiate_tag>\r\n" +
            "		<sctp.initiate_tag>0x71b81d1f</sctp.initiate_tag>\r\n" +
            "		<sctp.init_credit>109568</sctp.init_credit>\r\n" +
            "		<sctp.init_nr_out_streams>10</sctp.init_nr_out_streams>\r\n" +
            "		<sctp.init_nr_in_streams>65535</sctp.init_nr_in_streams>\r\n" +
            "		<sctp.init_initial_tsn>2702200202</sctp.init_initial_tsn>\r\n" +
            "		<Supported-address-types-parameter>\r\n" +
            "			<sctp.parameter_type>0x0000000c</sctp.parameter_type>\r\n" +
            "			<sctp.parameter_type_tree>\r\n" +
            "			<sctp.parameter_bit_1>0</sctp.parameter_bit_1>\r\n" +
            "			<sctp.parameter_bit_2>0</sctp.parameter_bit_2>\r\n" +
            "			</sctp.parameter_type_tree>\r\n" +
            "				<sctp.parameter_length>6</sctp.parameter_length>\r\n" +
            "				<sctp.parameter_supported_addres_type>5</sctp.parameter_supported_addres_type>\r\n" +
            "				<sctp.parameter_padding>00:00</sctp.parameter_padding>\r\n" +
            "				</Supported-address-types-parameter>\r\n" +
            "			<ECN-parameter>\r\n" +
            "				<sctp.parameter_type>0x00008000</sctp.parameter_type>\r\n" +
            "				<sctp.parameter_type_tree>\r\n" +
            "				<sctp.parameter_bit_1>1</sctp.parameter_bit_1>\r\n" +
            "				<sctp.parameter_bit_2>0</sctp.parameter_bit_2>\r\n" +
            "				</sctp.parameter_type_tree>\r\n" +
            "				<sctp.parameter_length>4</sctp.parameter_length>\r\n" +
            "			</ECN-parameter>\r\n" +
            "		<Forward-TSN-supported-parameter>\r\n" +
            "			<sctp.parameter_type>0x0000c000</sctp.parameter_type>\r\n" +
            "			<sctp.parameter_type_tree>\r\n" +
            "			<sctp.parameter_bit_1>1</sctp.parameter_bit_1>\r\n" +
            "			<sctp.parameter_bit_2>1</sctp.parameter_bit_2>\r\n" +
            "			</sctp.parameter_type_tree>\r\n" +
            "			<sctp.parameter_length>4</sctp.parameter_length>\r\n" +
            "		</Forward-TSN-supported-parameter>\r\n" +
            "	</INIT>\r\n" +
            "</sctp>";

    static String cookie_echo_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
            "<sctp>\r\n" +
            "	<sctp.srcport>6666</sctp.srcport>\r\n" +
            "	<sctp.dstport>9999</sctp.dstport>\r\n" +
            "	<sctp.verification_tag>0x48e63127</sctp.verification_tag>\r\n" +
            "	<sctp.assoc_index>0</sctp.assoc_index>\r\n" +
            "	<sctp.port>9999</sctp.port>\r\n" +
            "	<sctp.checksum>0x9517194d</sctp.checksum>\r\n" +
            "	<sctp.checksum.status>2</sctp.checksum.status>\r\n" +
            "	<COOKIE_ECHO>\r\n" +
            "		<sctp.chunk_type>10</sctp.chunk_type>\r\n" +
            "		<sctp.chunk_type_tree>\r\n" +
            "		<sctp.chunk_bit_1>0</sctp.chunk_bit_1>\r\n" +
            "		<sctp.chunk_bit_2>0</sctp.chunk_bit_2>\r\n" +
            "			</sctp.chunk_type_tree>\r\n" +
            "			<sctp.chunk_flags>0x00000000</sctp.chunk_flags>\r\n" +
            "			<sctp.chunk_length>164</sctp.chunk_length>\r\n" +
            "			<sctp.cookie>41:f6:c2:fe:87:3a:b1:64:60:4d:b8:52:4d:8a:33:d0:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:27:31:e6:48:1f:1d:b8:71:00:00:00:00:00:00:00:00:c5:31:da:41:1b:f6:03:00:0a:00:0a:00:5d:4a:fd:f9:02:00:0a:1a:c0:a8:00:65:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:01:00:00:00:00:00:00:00:01:00:00:24:71:b8:1d:1f:00:01:ac:00:00:0a:ff:ff:a1:10:4d:8a:00:0c:00:06:00:05:00:00:80:00:00:04:c0:00:00:04:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00:00</sctp.cookie>\r\n" +
            "	</COOKIE_ECHO>\r\n" +
            "</sctp>";

    static String data_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
            "<sctp>\r\n" +
            "					<sctp.srcport>6666</sctp.srcport>\r\n" +
            "					<sctp.dstport>9999</sctp.dstport>\r\n" +
            "					<sctp.verification_tag>0x48e63127</sctp.verification_tag>\r\n" +
            "					<sctp.assoc_index>0</sctp.assoc_index>\r\n" +
            "					<sctp.port>9999</sctp.port>\r\n" +
            "					<sctp.checksum>0x1adf8d92</sctp.checksum>\r\n" +
            "					<sctp.checksum.status>2</sctp.checksum.status>\r\n" +
            "					<DATA>\r\n" +
            "						<sctp.chunk_type>0</sctp.chunk_type>\r\n" +
            "						<sctp.chunk_type_tree>\r\n" +
            "							<sctp.chunk_bit_1>0</sctp.chunk_bit_1>\r\n" +
            "							<sctp.chunk_bit_2>0</sctp.chunk_bit_2>\r\n" +
            "						</sctp.chunk_type_tree>\r\n" +
            "						<sctp.chunk_flags>0x00000003</sctp.chunk_flags>\r\n" +
            "						<sctp.chunk_flags_tree>\r\n" +
            "							<sctp.data_e_bit>1</sctp.data_e_bit>\r\n" +
            "							<sctp.data_b_bit>1</sctp.data_b_bit>\r\n" +
            "							<sctp.data_u_bit>0</sctp.data_u_bit>\r\n" +
            "							<sctp.data_i_bit>0</sctp.data_i_bit>\r\n" +
            "						</sctp.chunk_flags_tree>\r\n" +
            "						<sctp.chunk_length>42</sctp.chunk_length>\r\n" +
            "						<sctp.data_tsn>2702200202</sctp.data_tsn>\r\n" +
            "						<sctp.data_tsn_tree>\r\n" +
            "							<sctp.acked>7</sctp.acked>\r\n" +
            "							<sctp.acked_tree>\r\n" +
            "								<sctp.data_rtt>0.000151000</sctp.data_rtt>\r\n" +
            "							</sctp.acked_tree>\r\n" +
            "						</sctp.data_tsn_tree>\r\n" +
            "						<sctp.data_sid>0x00000000</sctp.data_sid>\r\n" +
            "						<sctp.data_ssn>0</sctp.data_ssn>\r\n" +
            "						<sctp.data_payload_proto_id>0</sctp.data_payload_proto_id>\r\n" +
            "						<sctp.chunk_padding>00:00</sctp.chunk_padding>\r\n" +
            "					</DATA>\r\n" +
            "				</sctp>";

    static String shutdown_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
            "<sctp>\r\n" +
            "					<sctp.srcport>9999</sctp.srcport>\r\n" +
            "					<sctp.dstport>6666</sctp.dstport>\r\n" +
            "					<sctp.verification_tag>0x71b81d1f</sctp.verification_tag>\r\n" +
            "					<sctp.assoc_index>0</sctp.assoc_index>\r\n" +
            "					<sctp.port>6666</sctp.port>\r\n" +
            "					<sctp.checksum>0x818eb59b</sctp.checksum>\r\n" +
            "					<sctp.checksum.status>2</sctp.checksum.status>\r\n" +
            "					<SHUTDOWN>\r\n" +
            "						<sctp.chunk_type>7</sctp.chunk_type>\r\n" +
            "						<sctp.chunk_type_tree>\r\n" +
            "							<sctp.chunk_bit_1>0</sctp.chunk_bit_1>\r\n" +
            "							<sctp.chunk_bit_2>0</sctp.chunk_bit_2>\r\n" +
            "						</sctp.chunk_type_tree>\r\n" +
            "						<sctp.chunk_flags>0x00000000</sctp.chunk_flags>\r\n" +
            "						<sctp.chunk_length>8</sctp.chunk_length>\r\n" +
            "						<sctp.shutdown_cumulative_tsn_ack>2702200208</sctp.shutdown_cumulative_tsn_ack>\r\n" +
            "					</SHUTDOWN>\r\n" +
            "				</sctp>";

    static String shutdown_comp_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
            "<sctp>\r\n" +
            "					<sctp.srcport>9999</sctp.srcport>\r\n" +
            "					<sctp.dstport>6666</sctp.dstport>\r\n" +
            "					<sctp.verification_tag>0x71b81d1f</sctp.verification_tag>\r\n" +
            "					<sctp.assoc_index>0</sctp.assoc_index>\r\n" +
            "					<sctp.port>6666</sctp.port>\r\n" +
            "					<sctp.checksum>0x04227306</sctp.checksum>\r\n" +
            "					<sctp.checksum.status>2</sctp.checksum.status>\r\n" +
            "					<SHUTDOWN_COMPLETE>\r\n" +
            "						<sctp.chunk_type>14</sctp.chunk_type>\r\n" +
            "						<sctp.chunk_type_tree>\r\n" +
            "							<sctp.chunk_bit_1>0</sctp.chunk_bit_1>\r\n" +
            "							<sctp.chunk_bit_2>0</sctp.chunk_bit_2>\r\n" +
            "						</sctp.chunk_type_tree>\r\n" +
            "						<sctp.chunk_flags>0x00000000</sctp.chunk_flags>\r\n" +
            "						<sctp.chunk_flags_tree>\r\n" +
            "							<sctp.shutdown_complete_t_bit>0</sctp.shutdown_complete_t_bit>\r\n" +
            "						</sctp.chunk_flags_tree>\r\n" +
            "						<sctp.chunk_length>4</sctp.chunk_length>\r\n" +
            "					</SHUTDOWN_COMPLETE>\r\n" +
            "				</sctp>";

    public static void main(String args[]) throws IOException {

        // declaration section:
        // smtpClient: our client socket
        // os: output stream
        // is: input stream
        Socket sctpSocket = null;
        DataOutputStream os = null;
        DataInputStream is = null;
        String verficationTag = "0x71b81d1f";
        int srcPort = 66550, destPort = 1265;
        // Initialization section:
        // Try to open a socket on port 25
        // Try to open input and output streams
        try {
            destPort = 1265;
            System.out.println("***********Client***********");
            System.out.println("Connecting to host: ");
            System.out.println("Host ip: 127.0.0.1");
            System.out.println("Host port: " + destPort);
            sctpSocket = new Socket("127.0.0.1", destPort);
            srcPort = sctpSocket.getLocalPort();
            System.out.println("Client started at port: " + srcPort);
            //System.out.println("Client port: " + sctpSocket.getLocalPort());
            os = new DataOutputStream(sctpSocket.getOutputStream());
            is = new DataInputStream(sctpSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
        // If everything has been initialized then we want to write some data
        // to the socket we have opened a connection to on port 25
        if (sctpSocket != null && os != null && is != null) {
            try {
                // The capital string before each colon has a special meaning to SMTP
                // you may want to read the SMTP specification, RFC1822/3
                //os.writeUTF(init_xml);

                System.out.println("Sending INIT request with verification tag: " + verficationTag);
                os.writeUTF(XmlDataUtils.GetInitXml(srcPort, destPort, verficationTag));
                os.flush();
                // keep on reading from/to the socket till we receive the "Ok" from SMTP,
                // once we received that then we want to break.
                String responseLine;
                while ((responseLine = is.readUTF()) != null) {
                    System.out.println();
                    System.out.println("********XML Response from Server******");
                    System.out.println("--------------------------");
                    System.out.println(responseLine);
                    System.out.println("--------------------------");
                    System.out.println();
                    if (responseLine.contains("INIT_ACK")) {
                        //	System.out.println("init request to server");
                        //os.writeUTF(shutdown_xml);
                        //os.writeUTF(cookie_echo_xml);

                        System.out.println(">>>>INIT acknowledged by server with verification tag: " + verficationTag);
                        System.out.println(">>>>Sending COOKIE ECHO request.....");
                        os.writeUTF(XmlDataUtils.GetCookieEchoXml(srcPort, destPort, verficationTag));
                        os.flush();
                    } else if (responseLine.contains("COOKIE_ACK")) {
                        //os.writeUTF(data_xml);

                        System.out.println(">>>>Cookie is acknowledged by server");
                        System.out.println("Enter a message:");
                        Scanner scanner = new Scanner(System.in);
                        String msg = scanner.nextLine();
                        System.out.println(">>>>Sending data with message....");
                        os.writeUTF(XmlDataUtils.GetDataXml(srcPort, destPort, verficationTag, msg));
                        os.flush();
                    } else if (responseLine.contains("SACK")) {
                        System.out.println(">>>> Data is Acknowledged by server");
                        System.out.println();
                        int choice = -1;

                        while(choice != 1 && choice != 2){
                            System.out.println("Type following command for further action:");
                            System.out.println("------------------------------------------");
                            System.out.println("1 - Send another message");
                            System.out.println("2 - Shutdown connection");
                            Scanner scanner = new Scanner(System.in);
                            try{
                                choice = scanner.nextInt();
                            }
                            catch (Exception ex){
                                System.out.println("You must enter numeric value of the command");
                                ex.printStackTrace();
                            }
                        }

                        if(choice == 1){
                            System.out.println("Enter a message:");
                            Scanner scanner = new Scanner(System.in);
                            String msg = scanner.nextLine();
                            System.out.println(">>>>Sending data with message....");
                            os.writeUTF(XmlDataUtils.GetDataXml(srcPort, destPort, verficationTag, msg));
                            os.flush();
                        }
                        else if (choice == 2){
                            System.out.println();
                            System.out.println(">>>> Sending shutdown request.....");
                            os.writeUTF(XmlDataUtils.GetShutDownXml(srcPort, destPort, verficationTag));
                            os.flush();
                        }
                        //os.writeUTF(shutdown_xml);
                        //os.writeUTF(XmlDataUtils.GetShutDownXml(srcPort, destPort, verficationTag));
                        //os.flush();
                    } else if (responseLine.contains("SHUTDOWN_ACK")) {
                        System.out.println();
                        System.out.println(">>>> SHUTDOWN is acknowledged by server");
                        System.out.println(">>>> Sending SHUTDOWN_COMPLETE request....");
                        //os.writeUTF(shutdown_comp_xml);
                        os.writeUTF(XmlDataUtils.GetShutDownCompleteXml(srcPort, destPort, verficationTag));
                        os.flush();
                        break;
                        //os.close();
                        //is.close();

                    }


                    if (responseLine.indexOf("Ok") != -1) {
                        break;
                    }
                }
                // clean up:
                // close the output stream
                // close the input stream
                // close the socket
                System.out.println("********* Closing socket");
                System.out.println("********* Client closed");
                os.close();
                is.close();
                sctpSocket.close();
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }


}

	
	




