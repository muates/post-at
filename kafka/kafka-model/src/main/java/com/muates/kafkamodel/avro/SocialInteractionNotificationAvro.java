/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.muates.kafkamodel.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class SocialInteractionNotificationAvro extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -469238904215545028L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SocialInteractionNotificationAvro\",\"namespace\":\"com.muates.kafkamodel.avro\",\"fields\":[{\"name\":\"userId\",\"type\":\"long\"},{\"name\":\"postId\",\"type\":\"long\"},{\"name\":\"message\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"SocialInteractionType\",\"symbols\":[\"POST_LIKE\",\"POST_COMMENT\",\"COMMENT_LIKE\"]}},{\"name\":\"timestamp\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<SocialInteractionNotificationAvro> ENCODER =
      new BinaryMessageEncoder<SocialInteractionNotificationAvro>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<SocialInteractionNotificationAvro> DECODER =
      new BinaryMessageDecoder<SocialInteractionNotificationAvro>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<SocialInteractionNotificationAvro> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<SocialInteractionNotificationAvro> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<SocialInteractionNotificationAvro> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<SocialInteractionNotificationAvro>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this SocialInteractionNotificationAvro to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a SocialInteractionNotificationAvro from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a SocialInteractionNotificationAvro instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static SocialInteractionNotificationAvro fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private long userId;
   private long postId;
   private java.lang.String message;
   private com.muates.kafkamodel.avro.SocialInteractionType type;
   private long timestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public SocialInteractionNotificationAvro() {}

  /**
   * All-args constructor.
   * @param userId The new value for userId
   * @param postId The new value for postId
   * @param message The new value for message
   * @param type The new value for type
   * @param timestamp The new value for timestamp
   */
  public SocialInteractionNotificationAvro(java.lang.Long userId, java.lang.Long postId, java.lang.String message, com.muates.kafkamodel.avro.SocialInteractionType type, java.lang.Long timestamp) {
    this.userId = userId;
    this.postId = postId;
    this.message = message;
    this.type = type;
    this.timestamp = timestamp;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return userId;
    case 1: return postId;
    case 2: return message;
    case 3: return type;
    case 4: return timestamp;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: userId = (java.lang.Long)value$; break;
    case 1: postId = (java.lang.Long)value$; break;
    case 2: message = value$ != null ? value$.toString() : null; break;
    case 3: type = (com.muates.kafkamodel.avro.SocialInteractionType)value$; break;
    case 4: timestamp = (java.lang.Long)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'userId' field.
   * @return The value of the 'userId' field.
   */
  public long getUserId() {
    return userId;
  }


  /**
   * Sets the value of the 'userId' field.
   * @param value the value to set.
   */
  public void setUserId(long value) {
    this.userId = value;
  }

  /**
   * Gets the value of the 'postId' field.
   * @return The value of the 'postId' field.
   */
  public long getPostId() {
    return postId;
  }


  /**
   * Sets the value of the 'postId' field.
   * @param value the value to set.
   */
  public void setPostId(long value) {
    this.postId = value;
  }

  /**
   * Gets the value of the 'message' field.
   * @return The value of the 'message' field.
   */
  public java.lang.String getMessage() {
    return message;
  }


  /**
   * Sets the value of the 'message' field.
   * @param value the value to set.
   */
  public void setMessage(java.lang.String value) {
    this.message = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public com.muates.kafkamodel.avro.SocialInteractionType getType() {
    return type;
  }


  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(com.muates.kafkamodel.avro.SocialInteractionType value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return The value of the 'timestamp' field.
   */
  public long getTimestamp() {
    return timestamp;
  }


  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(long value) {
    this.timestamp = value;
  }

  /**
   * Creates a new SocialInteractionNotificationAvro RecordBuilder.
   * @return A new SocialInteractionNotificationAvro RecordBuilder
   */
  public static com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder newBuilder() {
    return new com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder();
  }

  /**
   * Creates a new SocialInteractionNotificationAvro RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new SocialInteractionNotificationAvro RecordBuilder
   */
  public static com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder newBuilder(com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder other) {
    if (other == null) {
      return new com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder();
    } else {
      return new com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder(other);
    }
  }

  /**
   * Creates a new SocialInteractionNotificationAvro RecordBuilder by copying an existing SocialInteractionNotificationAvro instance.
   * @param other The existing instance to copy.
   * @return A new SocialInteractionNotificationAvro RecordBuilder
   */
  public static com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder newBuilder(com.muates.kafkamodel.avro.SocialInteractionNotificationAvro other) {
    if (other == null) {
      return new com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder();
    } else {
      return new com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder(other);
    }
  }

  /**
   * RecordBuilder for SocialInteractionNotificationAvro instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SocialInteractionNotificationAvro>
    implements org.apache.avro.data.RecordBuilder<SocialInteractionNotificationAvro> {

    private long userId;
    private long postId;
    private java.lang.String message;
    private com.muates.kafkamodel.avro.SocialInteractionType type;
    private long timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.userId)) {
        this.userId = data().deepCopy(fields()[0].schema(), other.userId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.postId)) {
        this.postId = data().deepCopy(fields()[1].schema(), other.postId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.message)) {
        this.message = data().deepCopy(fields()[2].schema(), other.message);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.type)) {
        this.type = data().deepCopy(fields()[3].schema(), other.type);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[4].schema(), other.timestamp);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
    }

    /**
     * Creates a Builder by copying an existing SocialInteractionNotificationAvro instance
     * @param other The existing instance to copy.
     */
    private Builder(com.muates.kafkamodel.avro.SocialInteractionNotificationAvro other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.userId)) {
        this.userId = data().deepCopy(fields()[0].schema(), other.userId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.postId)) {
        this.postId = data().deepCopy(fields()[1].schema(), other.postId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.message)) {
        this.message = data().deepCopy(fields()[2].schema(), other.message);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.type)) {
        this.type = data().deepCopy(fields()[3].schema(), other.type);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[4].schema(), other.timestamp);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'userId' field.
      * @return The value.
      */
    public long getUserId() {
      return userId;
    }


    /**
      * Sets the value of the 'userId' field.
      * @param value The value of 'userId'.
      * @return This builder.
      */
    public com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder setUserId(long value) {
      validate(fields()[0], value);
      this.userId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'userId' field has been set.
      * @return True if the 'userId' field has been set, false otherwise.
      */
    public boolean hasUserId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'userId' field.
      * @return This builder.
      */
    public com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder clearUserId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'postId' field.
      * @return The value.
      */
    public long getPostId() {
      return postId;
    }


    /**
      * Sets the value of the 'postId' field.
      * @param value The value of 'postId'.
      * @return This builder.
      */
    public com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder setPostId(long value) {
      validate(fields()[1], value);
      this.postId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'postId' field has been set.
      * @return True if the 'postId' field has been set, false otherwise.
      */
    public boolean hasPostId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'postId' field.
      * @return This builder.
      */
    public com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder clearPostId() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'message' field.
      * @return The value.
      */
    public java.lang.String getMessage() {
      return message;
    }


    /**
      * Sets the value of the 'message' field.
      * @param value The value of 'message'.
      * @return This builder.
      */
    public com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder setMessage(java.lang.String value) {
      validate(fields()[2], value);
      this.message = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'message' field has been set.
      * @return True if the 'message' field has been set, false otherwise.
      */
    public boolean hasMessage() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'message' field.
      * @return This builder.
      */
    public com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder clearMessage() {
      message = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public com.muates.kafkamodel.avro.SocialInteractionType getType() {
      return type;
    }


    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder setType(com.muates.kafkamodel.avro.SocialInteractionType value) {
      validate(fields()[3], value);
      this.type = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder clearType() {
      type = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * @return The value.
      */
    public long getTimestamp() {
      return timestamp;
    }


    /**
      * Sets the value of the 'timestamp' field.
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder setTimestamp(long value) {
      validate(fields()[4], value);
      this.timestamp = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public com.muates.kafkamodel.avro.SocialInteractionNotificationAvro.Builder clearTimestamp() {
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SocialInteractionNotificationAvro build() {
      try {
        SocialInteractionNotificationAvro record = new SocialInteractionNotificationAvro();
        record.userId = fieldSetFlags()[0] ? this.userId : (java.lang.Long) defaultValue(fields()[0]);
        record.postId = fieldSetFlags()[1] ? this.postId : (java.lang.Long) defaultValue(fields()[1]);
        record.message = fieldSetFlags()[2] ? this.message : (java.lang.String) defaultValue(fields()[2]);
        record.type = fieldSetFlags()[3] ? this.type : (com.muates.kafkamodel.avro.SocialInteractionType) defaultValue(fields()[3]);
        record.timestamp = fieldSetFlags()[4] ? this.timestamp : (java.lang.Long) defaultValue(fields()[4]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<SocialInteractionNotificationAvro>
    WRITER$ = (org.apache.avro.io.DatumWriter<SocialInteractionNotificationAvro>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<SocialInteractionNotificationAvro>
    READER$ = (org.apache.avro.io.DatumReader<SocialInteractionNotificationAvro>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.userId);

    out.writeLong(this.postId);

    out.writeString(this.message);

    out.writeEnum(this.type.ordinal());

    out.writeLong(this.timestamp);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.userId = in.readLong();

      this.postId = in.readLong();

      this.message = in.readString();

      this.type = com.muates.kafkamodel.avro.SocialInteractionType.values()[in.readEnum()];

      this.timestamp = in.readLong();

    } else {
      for (int i = 0; i < 5; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.userId = in.readLong();
          break;

        case 1:
          this.postId = in.readLong();
          break;

        case 2:
          this.message = in.readString();
          break;

        case 3:
          this.type = com.muates.kafkamodel.avro.SocialInteractionType.values()[in.readEnum()];
          break;

        case 4:
          this.timestamp = in.readLong();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










