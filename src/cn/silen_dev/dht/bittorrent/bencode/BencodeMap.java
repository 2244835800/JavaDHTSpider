/*
    Copyright 2008, 2009 Wolfgang Ginolas

    This file is part of P2PVPN.

    P2PVPN is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package cn.silen_dev.dht.bittorrent.bencode;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map.Entry;

public class BencodeMap extends HashMap<BencodeString, BencodeObject> implements BencodeObject {

	public BencodeMap() {
		super();
	}

	public void write(OutputStream out) throws IOException {
		out.write('d');
		for (Entry<BencodeString, BencodeObject> e : this.entrySet()) {
			e.getKey().write(out);
			e.getValue().write(out);
		}
		out.write('e');
	}

	@Override
	public synchronized String toString() {
		StringBuffer result = new StringBuffer();
		boolean first = true;

		result.append('(');
		for (Entry<BencodeString, BencodeObject> e : this.entrySet()) {
			if (!first) result.append(", ");
			result.append(e.getKey()+":"+e.getValue());
			first = false;
		}
		result.append(')');
		return super.toString();
	}
}
