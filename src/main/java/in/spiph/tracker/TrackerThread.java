/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.tracker;

import in.spiph.packets.APacket;
import in.spiph.packets.ErrorPacket;
import in.spiph.packets.packetmanager.BufferedManager;
import in.spiph.packets.packetmanager.IPacketManager;
import in.spiph.packets.test.TestRespPacket;
import in.spiph.packets.iplookup.IpLookupPacket;
import in.spiph.packets.iplookup.IpLookupResponsePacket;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Gabriel.Maxfield, Bennett.DenBleyker
 */
public class TrackerThread implements Runnable {

    private final Socket socket;
    private IPacketManager pman;

    public TrackerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            pman = new BufferedManager(this.socket);
            APacket packet;
            while ((packet = pman.readPacket()) != null) {
                APacket outPacket = handlePacket(packet);
                pman.sendPacket(outPacket);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            pman.close();
        }
    }

    private void updateIdToIP(long id, String ip) {
        Application.IPMAP.put(id, ip);
    }

    private String getIpFromId(long id) {
        return Application.IPMAP.getOrDefault(id, "0.0.0.0");
    }
    
    private Long getPKeyFromId(long id) {
        return Application.PKEYMAP.getOrDefault(id, -1L);
    }

    private APacket handlePacket(APacket packet) {
        switch (packet.type) {
            case 1://Test
                return new TestRespPacket();
            case 3:
                return new IpLookupResponsePacket(getIpFromId(((IpLookupPacket) packet).id));
            default:
                return new ErrorPacket();
        }
    }

}
