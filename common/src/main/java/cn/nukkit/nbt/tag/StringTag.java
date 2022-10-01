package cn.nukkit.nbt.tag;

import cn.nukkit.nbt.stream.NBTInputStream;
import cn.nukkit.nbt.stream.NBTOutputStream;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Objects;

public class StringTag extends Tag {
    public String data;

    public StringTag(String name) {
        super(name);
    }

    public StringTag(String name, @Nonnull String data) {
        super(name);
        if(data == null) {
            throw new IllegalArgumentException("Empty string not allowed");
        } else {
            this.data = data;
        }
    }

    @Override
    void write(NBTOutputStream dos) throws IOException {
        dos.writeUTF(data);
    }

    @Override
    void load(NBTInputStream dis) throws IOException {
        data = dis.readUTF();
    }

    @Override
    public String parseValue() {
        return this.data;
    }

    @Override
    public byte getId() {
        return TAG_String;
    }

    @Override
    public String toString() {
        return "StringTag " + this.getName() + " (data: " + data + ")";
    }

    @Override
    public Tag copy() {
        return new StringTag(getName(), data);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            StringTag o = (StringTag) obj;
            return ((data == null && o.data == null) || (data != null && data.equals(o.data)));
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data);
    }
}
