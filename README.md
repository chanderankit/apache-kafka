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
