/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})

public class MyRateRecordResponse implements org.apache.thrift.TBase<MyRateRecordResponse, MyRateRecordResponse._Fields>, java.io.Serializable, Cloneable, Comparable<MyRateRecordResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("MyRateRecordResponse");

  private static final org.apache.thrift.protocol.TField ALREADY_RATED_FIELD_DESC = new org.apache.thrift.protocol.TField("alreadyRated", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField MY_RATE_FIELD_DESC = new org.apache.thrift.protocol.TField("myRate", org.apache.thrift.protocol.TType.DOUBLE, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new MyRateRecordResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new MyRateRecordResponseTupleSchemeFactory());
  }

  public boolean alreadyRated; // required
  public double myRate; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ALREADY_RATED((short)1, "alreadyRated"),
    MY_RATE((short)2, "myRate");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ALREADY_RATED
          return ALREADY_RATED;
        case 2: // MY_RATE
          return MY_RATE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ALREADYRATED_ISSET_ID = 0;
  private static final int __MYRATE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ALREADY_RATED, new org.apache.thrift.meta_data.FieldMetaData("alreadyRated", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.MY_RATE, new org.apache.thrift.meta_data.FieldMetaData("myRate", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(MyRateRecordResponse.class, metaDataMap);
  }

  public MyRateRecordResponse() {
    this.myRate = 0;

  }

  public MyRateRecordResponse(
    boolean alreadyRated,
    double myRate)
  {
    this();
    this.alreadyRated = alreadyRated;
    setAlreadyRatedIsSet(true);
    this.myRate = myRate;
    setMyRateIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public MyRateRecordResponse(MyRateRecordResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    this.alreadyRated = other.alreadyRated;
    this.myRate = other.myRate;
  }

  public MyRateRecordResponse deepCopy() {
    return new MyRateRecordResponse(this);
  }

  @Override
  public void clear() {
    setAlreadyRatedIsSet(false);
    this.alreadyRated = false;
    this.myRate = 0;

  }

  public boolean isAlreadyRated() {
    return this.alreadyRated;
  }

  public MyRateRecordResponse setAlreadyRated(boolean alreadyRated) {
    this.alreadyRated = alreadyRated;
    setAlreadyRatedIsSet(true);
    return this;
  }

  public void unsetAlreadyRated() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ALREADYRATED_ISSET_ID);
  }

  /** Returns true if field alreadyRated is set (has been assigned a value) and false otherwise */
  public boolean isSetAlreadyRated() {
    return EncodingUtils.testBit(__isset_bitfield, __ALREADYRATED_ISSET_ID);
  }

  public void setAlreadyRatedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ALREADYRATED_ISSET_ID, value);
  }

  public double getMyRate() {
    return this.myRate;
  }

  public MyRateRecordResponse setMyRate(double myRate) {
    this.myRate = myRate;
    setMyRateIsSet(true);
    return this;
  }

  public void unsetMyRate() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MYRATE_ISSET_ID);
  }

  /** Returns true if field myRate is set (has been assigned a value) and false otherwise */
  public boolean isSetMyRate() {
    return EncodingUtils.testBit(__isset_bitfield, __MYRATE_ISSET_ID);
  }

  public void setMyRateIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MYRATE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ALREADY_RATED:
      if (value == null) {
        unsetAlreadyRated();
      } else {
        setAlreadyRated((Boolean)value);
      }
      break;

    case MY_RATE:
      if (value == null) {
        unsetMyRate();
      } else {
        setMyRate((Double)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ALREADY_RATED:
      return isAlreadyRated();

    case MY_RATE:
      return getMyRate();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ALREADY_RATED:
      return isSetAlreadyRated();
    case MY_RATE:
      return isSetMyRate();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof MyRateRecordResponse)
      return this.equals((MyRateRecordResponse)that);
    return false;
  }

  public boolean equals(MyRateRecordResponse that) {
    if (that == null)
      return false;

    boolean this_present_alreadyRated = true;
    boolean that_present_alreadyRated = true;
    if (this_present_alreadyRated || that_present_alreadyRated) {
      if (!(this_present_alreadyRated && that_present_alreadyRated))
        return false;
      if (this.alreadyRated != that.alreadyRated)
        return false;
    }

    boolean this_present_myRate = true;
    boolean that_present_myRate = true;
    if (this_present_myRate || that_present_myRate) {
      if (!(this_present_myRate && that_present_myRate))
        return false;
      if (this.myRate != that.myRate)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_alreadyRated = true;
    list.add(present_alreadyRated);
    if (present_alreadyRated)
      list.add(alreadyRated);

    boolean present_myRate = true;
    list.add(present_myRate);
    if (present_myRate)
      list.add(myRate);

    return list.hashCode();
  }

  @Override
  public int compareTo(MyRateRecordResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAlreadyRated()).compareTo(other.isSetAlreadyRated());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAlreadyRated()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.alreadyRated, other.alreadyRated);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMyRate()).compareTo(other.isSetMyRate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMyRate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.myRate, other.myRate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("MyRateRecordResponse(");
    boolean first = true;

    sb.append("alreadyRated:");
    sb.append(this.alreadyRated);
    first = false;
    if (!first) sb.append(", ");
    sb.append("myRate:");
    sb.append(this.myRate);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class MyRateRecordResponseStandardSchemeFactory implements SchemeFactory {
    public MyRateRecordResponseStandardScheme getScheme() {
      return new MyRateRecordResponseStandardScheme();
    }
  }

  private static class MyRateRecordResponseStandardScheme extends StandardScheme<MyRateRecordResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, MyRateRecordResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ALREADY_RATED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.alreadyRated = iprot.readBool();
              struct.setAlreadyRatedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MY_RATE
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.myRate = iprot.readDouble();
              struct.setMyRateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, MyRateRecordResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ALREADY_RATED_FIELD_DESC);
      oprot.writeBool(struct.alreadyRated);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(MY_RATE_FIELD_DESC);
      oprot.writeDouble(struct.myRate);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class MyRateRecordResponseTupleSchemeFactory implements SchemeFactory {
    public MyRateRecordResponseTupleScheme getScheme() {
      return new MyRateRecordResponseTupleScheme();
    }
  }

  private static class MyRateRecordResponseTupleScheme extends TupleScheme<MyRateRecordResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, MyRateRecordResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetAlreadyRated()) {
        optionals.set(0);
      }
      if (struct.isSetMyRate()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetAlreadyRated()) {
        oprot.writeBool(struct.alreadyRated);
      }
      if (struct.isSetMyRate()) {
        oprot.writeDouble(struct.myRate);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, MyRateRecordResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.alreadyRated = iprot.readBool();
        struct.setAlreadyRatedIsSet(true);
      }
      if (incoming.get(1)) {
        struct.myRate = iprot.readDouble();
        struct.setMyRateIsSet(true);
      }
    }
  }

}

