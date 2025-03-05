package com.ball_game.app.util;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

public class PortScanner {

    static String local_ip = "10.0.0.0";

    public static void main(String[] args) {
        List<String> openIPs = checkOpenPortOnLocalIPs(8080);
        
        if (openIPs.isEmpty()) {
            System.out.println("No IPs found with port 8080 open.");
        } else {
            System.out.println("IPs with port 8080 open: " + openIPs);
        }
    }

    public static List<String> checkOpenPortOnLocalIPs(int port) {
        List<String> openIPs = new ArrayList<>();
        List<Future<String>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(50);
        
        try {
            String subnet = local_ip.substring(0, local_ip.lastIndexOf('.') + 1);
            
            for (int i = 1; i < 255; i++) {
                String testIP = subnet + i;
                futures.add(executor.submit(new PortCheckTask(testIP, port)));
            }
            
            for (Future<String> future : futures) {
                try {
                    String result = future.get();
                    if (result != null) {
                        openIPs.add(result);
                    }
                } catch (Exception e) {
                    System.err.println("Error retrieving result: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error getting local host address: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
        
        return openIPs;
    }

    static class PortCheckTask implements Callable<String> {
        private final String ip;
        private final int port;

        public PortCheckTask(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        @Override
        public String call() {
            if (isPortOpen(ip, port, 200)) {
                return ip;
            }
            return null;
        }
    }

    public static boolean isPortOpen(String ip, int port, int timeout) {
        try (Socket socket = new Socket()) {
            socket.connect(new java.net.InetSocketAddress(ip, port), timeout);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}