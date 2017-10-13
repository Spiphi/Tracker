/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.packets.iplookup;

import in.spiph.packets.APacket;

/**
 *
 * @author Gabriel.Maxfield
 */
public class IpLookupResponsePacket extends APacket {

    public final String ip;

    public IpLookupResponsePacket(String ip) {
        super(4, new char[4]);
        this.ip = ip;
        String tokens[] = ip.split("[.]");
        for (int i = 0; i < tokens.length; i++) {
            this.data[i] = (char) Integer.parseInt(tokens[i]);
        }
    }

    public IpLookupResponsePacket(char data[]) {
        super(4, data);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            str.append((int) data[i]);
            if (i != data.length - 1) {
                str.append('.');
            }
        }
        this.ip = str.toString();
    }

    @Override
    public String toString() {
        return super.toString() + " Ip: " + ip;
    }
}
