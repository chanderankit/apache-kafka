# 🚀 Apache Kafka (KRaft Mode) Setup on Windows

This guide walks you through setting up **Apache Kafka in KRaft mode (no ZooKeeper)** on a Windows machine.

---

## 🧰 Prerequisites

* Java **17 or later** installed
* Kafka **binary (ZIP)** downloaded from the [official site](https://kafka.apache.org/downloads)

---

## 📦 1. Download & Extract Kafka

1. Download the Kafka ZIP archive.
2. Extract it to a directory, e.g., `C:\kafka`.

---

## ⚙️ 2. Initialize Kafka Logs (KRaft Requirement)

Kafka running in **KRaft mode** requires log directory initialization.

### Steps:

1. Generate a UUID:

   ```powershell
   powershell -Command "[guid]::NewGuid().ToString()"
   ```

2. Format the storage using the UUID:

   ```bash
   bin\windows\kafka-storage.bat format -t YOUR_UUID -c config\server.properties --standalone
   ```

> ⚠️ Run this command **only once** before starting Kafka.

---

## 🟢 3. Start Kafka (ZooKeeper-Free)

Start the Kafka broker using:

```bash
bin\windows\kafka-server-start.bat config\server.properties
```

> 🧠 No ZooKeeper is needed in KRaft mode.

---

## 🧵 4. Create a Topic

```bash
bin\windows\kafka-topics.bat --create --topic resign-kafka --bootstrap-server localhost:9092
```

---

## 📤 5. Start a Producer

```bash
bin\windows\kafka-console-producer.bat --topic resign-kafka --bootstrap-server localhost:9092
```

Type messages — they’ll be published to the topic.

---

## 📥 6. Start a Consumer

```bash
bin\windows\kafka-console-consumer.bat --topic resign-kafka --from-beginning --bootstrap-server localhost:9092
```

This will read all messages from the beginning of the topic.

---

## ✅ Done!

You now have a fully functioning Kafka setup using **KRaft mode** on Windows 🎉

---

## 📌 Notes

* Ensure ports like **9092** are not blocked by firewall/antivirus.
* You can customize `config\server.properties` for:

  * Log directory paths
  * Broker ID
  * Advertised listeners
  * Other Kafka settings

---

# ✅ Kafka Config Cheat Sheet (for Interviews & Daily Use)

---

## 🔹 General Configs

| Config | Description | Example |
|--------|-------------|---------|
| `bootstrap.servers` | List of Kafka broker addresses | `spring.kafka.bootstrap-servers=localhost:9092` |

---

## 🔹 Producer Configs

| Config | Purpose | Example | Notes |
|--------|---------|---------|-------|
| `acks` | Message durability level | `all`, `1`, `0` | `all` = safest |
| `retries` | Retry count on failure | `spring.kafka.producer.retries=3` | Avoids transient failures |
| `batch.size` | Max bytes to batch before sending | `spring.kafka.producer.batch-size=16384` | Batching = better throughput |
| `linger.ms` | Time to wait before sending batch | `spring.kafka.producer.linger-ms=5` | Small delay for better batching |
| `compression.type` | Compress payload to reduce size | `gzip`, `snappy`, `lz4`, `zstd` | Works after serialization |
| `enable.idempotence` | Avoid duplicate messages | `spring.kafka.producer.properties.enable.idempotence=true` | Enables exactly-once |
| `max.request.size` | Max size of a message request | (if needed) | Increase for large messages |

---

## 🔹 Consumer Configs

| Config | Purpose | Example | Notes |
|--------|---------|---------|-------|
| `group.id` | Group of consumers sharing work | `spring.kafka.consumer.group-id=my-group` | Enables load balancing |
| `auto.offset.reset` | Where to read from if no offset exists | `earliest`, `latest`, `none` | `earliest` = from beginning |
| `enable.auto.commit` | Auto-commit offsets? | `false` = manual commit | Manual commit is safer |
| `max.poll.records` | Max records per poll() | `spring.kafka.consumer.max-poll-records=500` | Helps control processing batch size |
| `session.timeout.ms` | Max time before consumer considered dead | `spring.kafka.consumer.session-timeout-ms=10000` | Must be > heartbeat |
| `heartbeat.interval.ms` | Time between heartbeats | `spring.kafka.consumer.heartbeat-interval-ms=3000` | Ping to Kafka |
| `max.poll.interval.ms` | Max time between polls | Default: 5 min | Exceeding causes rebalance |

---

## 🔹 Performance Tuning Scenarios

| Goal | Key Configs |
|------|-------------|
| **High throughput** | `batch.size`, `linger.ms`, `compression.type`, `acks=1` |
| **Low latency** | `linger.ms=0`, low `batch.size` |
| **High durability** | `acks=all`, `retries`, `enable.idempotence=true` |
| **Avoid duplicates** | `enable.idempotence=true`, proper retry logic |
| **Handle slow consumers** | `max.poll.interval.ms`, `session.timeout.ms` |

---
