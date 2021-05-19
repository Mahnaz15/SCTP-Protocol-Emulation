package XmlUtils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class XmlDataUtils {

    public static String toHex(String arg) {
        return String.format("%x", new BigInteger(1, arg.getBytes(Charset.forName("UTF-8"))));
    }

    public static String toString(String hex){
        ByteBuffer buff = ByteBuffer.allocate(hex.length()/2);
        for (int i = 0; i < hex.length(); i+=2) {
            buff.put((byte)Integer.parseInt(hex.substring(i, i+2), 16));
        }
        buff.rewind();
        Charset cs = Charset.forName("UTF-8");
        return cs.decode(buff).toString();
    }

    public static String insertPeriodically(String text, String insert, int period){
        StringBuilder builder = new StringBuilder(
                text.length() + insert.length() * (text.length()/period)+1);

        int index = 0;
        String prefix = "";
        while (index < text.length())
        {
            // Don't put the insert in the very first iteration.
            // This is easier than appending it *after* each substring
            builder.append(prefix);
            prefix = insert;
            builder.append(text.substring(index,
                    Math.min(index + period, text.length())));
            index += period;
        }
        return builder.toString();
    }

    public static String GetInitXml(int srcPort, int destPort, String verficationTag) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
                "\r\n" +
                "<sctp>\r\n" +
                "	<sctp.srcport>" + srcPort + "</sctp.srcport>\r\n" +
                "	<sctp.dstport>" + destPort + "</sctp.dstport>\r\n" +
                "	<sctp.verification_tag>" + verficationTag + "</sctp.verification_tag>\r\n" +
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
    }

    public static String GetInitAckXml(int srcPort, int destPort, String verficationTag){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
                "<sctp>\r\n" +
                "					<sctp.srcport>" + srcPort + "</sctp.srcport>\r\n" +
                "					<sctp.dstport>" + destPort + "</sctp.dstport>\r\n" +
                "					<sctp.verification_tag>" + verficationTag + "</sctp.verification_tag>\r\n" +
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
    }

    public static String GetCookieEchoXml(int srcPort, int destPort, String verificationTag) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
                "<sctp>\r\n" +
                "	<sctp.srcport>" + srcPort + "</sctp.srcport>\r\n" +
                "	<sctp.dstport>" + destPort + "</sctp.dstport>\r\n" +
                "	<sctp.verification_tag>" + verificationTag + "</sctp.verification_tag>\r\n" +
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
    }

    public static String GetCookieAckXml(int srcPort, int destPort, String verficationTag){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
                "<sctp>\r\n" +
                "					<sctp.srcport>" + srcPort + "</sctp.srcport>\r\n" +
                "					<sctp.dstport>" + destPort + "</sctp.dstport>\r\n" +
                "					<sctp.verification_tag>" + verficationTag + "</sctp.verification_tag>\r\n" +
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
    }

    public static String GetSackAckXml(int srcPort, int destPort, String verficationTag){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
                "<sctp>\r\n" +
                "					<sctp.srcport>" + srcPort + "</sctp.srcport>\r\n" +
                "					<sctp.dstport>" + destPort + "</sctp.dstport>\r\n" +
                "					<sctp.verification_tag>" + verficationTag + "</sctp.verification_tag>\r\n" +
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
    }

    public static String GetShudownAckXml(int srcPort, int destPort, String verficationTag){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
                "<sctp>\r\n" +
                "					<sctp.srcport>" + srcPort + "</sctp.srcport>\r\n" +
                "					<sctp.dstport>" + destPort + "</sctp.dstport>\r\n" +
                "					<sctp.verification_tag>" + verficationTag + "</sctp.verification_tag>\r\n" +
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
    }

    public static String GetDataXml(int srcPort, int destPort, String verificationTag, String dataMsg) {
        String data = XmlDataUtils.insertPeriodically(XmlDataUtils.toHex(dataMsg), ":", 2);
        int dataLength = data.split(":").length;
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
                "<sctp>\r\n" +
                "					<sctp.srcport>" + srcPort + "</sctp.srcport>\r\n" +
                "					<sctp.dstport>" + destPort + "</sctp.dstport>\r\n" +
                "					<sctp.verification_tag>" + verificationTag + "</sctp.verification_tag>\r\n" +
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
                "				</sctp>\r\n" +
                "               <data>\r\n" +
                "                   <data.data>" + data + "</data.data>\r\n" +
                "                   <data.len>" + dataLength + "</data.len>\r\n" +
                "               </data>";
    }

    public static String GetShutDownXml(int srcPort, int destPort, String verificationTag){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
                "<sctp>\r\n" +
                "					<sctp.srcport>" + srcPort + "</sctp.srcport>\r\n" +
                "					<sctp.dstport>" + destPort + "</sctp.dstport>\r\n" +
                "					<sctp.verification_tag>" + verificationTag + "</sctp.verification_tag>\r\n" +
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
    }

    public static String GetShutDownCompleteXml(int srcPort, int destPort, String verificationTag){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" +
                "<sctp>\r\n" +
                "					<sctp.srcport>" + srcPort + "</sctp.srcport>\r\n" +
                "					<sctp.dstport>" + destPort + "</sctp.dstport>\r\n" +
                "					<sctp.verification_tag>" + verificationTag + "</sctp.verification_tag>\r\n" +
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
    }
}
