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
public class IpLookupPacket extends APacket {

    public final long id;

    public IpLookupPacket(long id) {
        super(3, new char[]{(char) ((id >> 24) & 0xFF), (char) ((id >> 16) & 0xFF), (char) ((id >> 8) & 0xFF), (char) (id & 0xFF)});
        this.id = id;
    }

    public IpLookupPacket(char[] data) {
        super(3, data);
        this.id = (data[0] << 24) | (data[1] << 16) | (data[2] << 8) | data[3];
    }

}
