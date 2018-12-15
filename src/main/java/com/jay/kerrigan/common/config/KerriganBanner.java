package com.jay.kerrigan.common.config;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import com.jay.kerrigan.Kerrigan;
import com.jay.kerrigan.KerriganMaster;
import com.jay.kerrigan.KerriganSlave;

public class KerriganBanner implements Banner {

	@Override
	public void printBanner(Environment environment, Class<?> sourceClass, PrintStream stream) {
		stream.println();
		if (Kerrigan.getKerriganRole() == KerriganMaster.class) {
			stream.println("  ██ ▄█▀▓█████  ██▀███   ██▀███   ██▓  ▄████  ▄▄▄       ███▄    █  ");
			stream.println("  ██▄█▒ ▓█   ▀ ▓██ ▒ ██▒▓██ ▒ ██▒▓██▒ ██▒ ▀█▒▒████▄     ██ ▀█   █  ");
			stream.println(" ▓███▄░ ▒███   ▓██ ░▄█ ▒▓██ ░▄█ ▒▒██▒▒██░▄▄▄░▒██  ▀█▄  ▓██  ▀█ ██▒ ");
			stream.println(" ▓██ █▄ ▒▓█  ▄ ▒██▀▀█▄  ▒██▀▀█▄  ░██░░▓█  ██▓░██▄▄▄▄██ ▓██▒  ▐▌██▒ ╔╦╗┌─┐┌─┐┌┬┐┌─┐┬─┐");
			stream.println(" ▒██▒ █▄░▒████▒░██▓ ▒██▒░██▓ ▒██▒░██░░▒▓███▀▒ ▓█   ▓██▒▒██░   ▓██░ ║║║├─┤└─┐ │ ├┤ ├┬┘");
			stream.println(" ▒ ▒▒ ▓▒░░ ▒░ ░░ ▒▓ ░▒▓░░ ▒▓ ░▒▓░░▓   ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ▒ ▒  ╩ ╩┴ ┴└─┘ ┴ └─┘┴└─");
			stream.println(" ░ ░▒ ▒░ ░ ░  ░  ░▒ ░ ▒░  ░▒ ░ ▒░ ▒ ░  ░   ░   ▒   ▒▒ ░░ ░░   ░ ▒░ ");
			stream.println(" ░ ░░ ░    ░     ░░   ░   ░░   ░  ▒ ░░ ░   ░   ░   ▒      ░   ░ ░  ");
			stream.println(" ░  ░      ░  ░   ░        ░      ░        ░       ░  ░         ░  ");
		}
		if (Kerrigan.getKerriganRole() == KerriganSlave.class) {
			stream.println("  ██ ▄█▀▓█████  ██▀███   ██▀███   ██▓  ▄████  ▄▄▄       ███▄    █  ");
			stream.println("  ██▄█▒ ▓█   ▀ ▓██ ▒ ██▒▓██ ▒ ██▒▓██▒ ██▒ ▀█▒▒████▄     ██ ▀█   █  ");
			stream.println(" ▓███▄░ ▒███   ▓██ ░▄█ ▒▓██ ░▄█ ▒▒██▒▒██░▄▄▄░▒██  ▀█▄  ▓██  ▀█ ██▒ ");
			stream.println(" ▓██ █▄ ▒▓█  ▄ ▒██▀▀█▄  ▒██▀▀█▄  ░██░░▓█  ██▓░██▄▄▄▄██ ▓██▒  ▐▌██▒ ╔═╗┬  ┌─┐┬  ┬┌─┐");
			stream.println(" ▒██▒ █▄░▒████▒░██▓ ▒██▒░██▓ ▒██▒░██░░▒▓███▀▒ ▓█   ▓██▒▒██░   ▓██░ ╚═╗│  ├─┤└┐┌┘├┤ ");
			stream.println(" ▒ ▒▒ ▓▒░░ ▒░ ░░ ▒▓ ░▒▓░░ ▒▓ ░▒▓░░▓   ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ▒ ▒  ╚═╝┴─┘┴ ┴ └┘ └─┘");
			stream.println(" ░ ░▒ ▒░ ░ ░  ░  ░▒ ░ ▒░  ░▒ ░ ▒░ ▒ ░  ░   ░   ▒   ▒▒ ░░ ░░   ░ ▒░ ");
			stream.println(" ░ ░░ ░    ░     ░░   ░   ░░   ░  ▒ ░░ ░   ░   ░   ▒      ░   ░ ░  ");
			stream.println(" ░  ░      ░  ░   ░        ░      ░        ░       ░  ░         ░  ");
		}
	}
}
