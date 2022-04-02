# RITGlove: A Java interface to the 5DT glove.
# Copyright (C) Damian Eads, 2004-2005.
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 2
# of the License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.


# Sorry. This makefile is a little messy but it should get you started.

all:		libglove.so

libglove.so:	src/Glove.h
	gcc -I/usr/j2sdk1.4.2_05/include/linux -I/usr/j2sdk1.4.2_05/include -shared -fPIC -c src/Glove.cpp
	gcc -shared -o libglove.so Glove.o

src/Glove.h:	gnu/dtools/ritglove/NativeGlove.class
	javah -classpath . gnu.dtools.ritglove.NativeGlove
	mv gnu_dtools_ritglove_NativeGlove.h src/Glove.h

gnu/dtools/ritglove/NativeGlove.class:
	javac gnu/dtools/ritglove/*.java

doc:
	javadoc -d doc gnu.dtools.ritglove;

clean:
	rm src/Glove.h
	rm gnu/dtools/ritglove/*.class
	rm libglove.so
