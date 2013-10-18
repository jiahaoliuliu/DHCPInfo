package com.jiahaoliuliu.dhcpinfo;

import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;

public class DHCPInfo extends Activity {

	private String dns1;
	private String dns2;
	private String gateway;
	private String ipAddress;
	private String leaseDuration;
	private String netmask;
	private String serverAddress;
	
	private TextView textView;
	private DhcpInfo dhcpInfo;
	private WifiManager wifiManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dhcpinfo);
		textView = (TextView)findViewById(R.id.textView);
	}
	
	protected void onResume() {
		super.onResume();
		wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		
		if (wifiManager == null) {
			return;
		}

		dhcpInfo = wifiManager.getDhcpInfo();

		if (dhcpInfo == null) {
			return;
		}
		
		dns1 = "DNS 1: " + int2Ip(dhcpInfo.dns1) + "\n";
		dns2 = "DNS 2: " + int2Ip(dhcpInfo.dns2) + "\n";
		gateway = "Gateway: " + int2Ip(dhcpInfo.gateway) + "\n";
		ipAddress = "IP Address: " + int2Ip(dhcpInfo.ipAddress) + "\n";
		leaseDuration = "Lease Time: " + String.valueOf(dhcpInfo.leaseDuration) + "\n";
		netmask = "Netmask: " + int2Ip(dhcpInfo.netmask) + "\n";
		serverAddress = "Server IP: " + int2Ip(dhcpInfo.serverAddress) + "\n";

		textView.setText(dns1 + dns2 + gateway + ipAddress + leaseDuration + netmask + serverAddress);
	}

	private String int2Ip(int i) {
		return (( i & 0xFF) + "." +
				((i >> 8) & 0xFF) + "." +
				((i >> 16) & 0xFF) + "." +
				((i >> 24) & 0xFF));
	}
}
