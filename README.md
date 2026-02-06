# notepad_apps
Playground for building notepad apps in different languages

Purpose is to explore other languages with simple use case

---

### pynotes
The notepad app writen in Python

To run make sure Python is installed on your machine

```
$ cd pynotes
$ python main.py
```

---
### javanotes
The notepad app writen in Java. To run make sure Java is installed on you machine:
[Install Java](https://www.java.com/en/download/manual.jsp). Or install via terminal such as on Mac (with fish):
```
$ brew install openjdk
$ fish_add_path /opt/homebrew/opt/openjdk/bin
```

Compile and open:
```
$ cd javanotes
$ javac SwingNotePad.java # To Compile
$ java SwingNotePad # To Open
```

---
### cppnotes
The notepad app writen in C++
To compile and run make sure you have qtmake installed

On Mac steps will look like:
```
cd cppnotes
qtmake notepad.pro 
make
./notepad.app/Contents/MacOS/notepad # to run
```