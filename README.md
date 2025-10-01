# File Explorer Search

A simple Java project that demonstrates different **search strategies** for scanning `.txt` files in a directory to find a given keyword.

It includes:

* **Sequential Search** – scans files one by one.
* **CompletableFuture Search** – processes files concurrently using `CompletableFuture`.
* **ForkJoin Search** – parallel search using the Fork/Join framework.
* **Test File Generator** – utility to generate thousands of `.txt` files with random content (and one file containing the search word).

---

## 🚀 How It Works

1. Place `.txt` files inside the target folder (or use the generator to create them).
2. Choose a search strategy (`SequentialSearchStrategy`, `CompletableFutureSearchStrategy`, or `ForkJoinSearchStrategy`).
3. Run the search with the target keyword.
4. The program prints the file(s) and line(s) containing the keyword.

---

## 🛠️ Usage

### Generate Test Files

```bash
java com.adel.explorer.TestFileGenerator testfiles
```

This will create 2000 `.txt` files in the `testfiles/` directory, with one file containing the word `searchword`.

## 📂 Project Structure

```
src/
 └── com/adel/explorer/
     ├── TestFileGenerator.java
     └── search/
         ├── SearchStrategy.java
         ├── SequentialSearchStrategy.java
         ├── CompletableFutureSearchStrategy.java
         └── ForkJoinSearchStrategy.java
```

---

## 🎯 Key Learning Points

* How to implement **strategy design pattern** in Java.
* Using **CompletableFuture** for asynchronous tasks.
* Leveraging **ForkJoinPool** for parallel processing.
* Stress testing with **large number of files**.
