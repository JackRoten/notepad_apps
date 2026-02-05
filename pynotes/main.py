import tkinter as tk
from tkinter import filedialog, messagebox, scrolledtext

def new_file():
    """
    Create a new file (clear the text area)
    """
    text_area.delete(1.0, tk.END)
    root.title("Untitled - Notepad")

def open_file():
    """
    Open a text file
    """
    file_path = filedialog.askopenfilename(defaultextension=".txt", 
    filetypes=[("Text Documents", "*.txt"), ("All Files", "*.*")])

    if file_path:
        try:
            with open(file_path, "r") as file:
                text_area.delete(1.0, tk.END)
                text_area.insert(tk.INSERT, file.read())
            root.title(f"{file_path} - Notepad")
        except Exception as e:
            messagebox.showerror("Error Opening File", f"An Error occured: {e}")

def save_file():
    """
    Save the current text to a file
    """
    file_path = filedialog.asksaveasfilename(defaultextension=".txt",
    filetypes=[("Text Documents", "*.txt"), ("All Files", "*.*")])

    if file_path:
        try:
            with open(file_path, "w") as file:
                file.write(text_area.get(1.0, tk.END))
            root.title(f"{file_path} - Notepad")
        except Exception as e:
            messagebox.showerror("Error Saving File", f"An Error occured: {e}")

def exit_app():
    """
    Exit the application
    """
    if messagebox.askyesno("Exit", "Are you sure you want to exit?"):
        root.destroy()

# Init the main window
root = tk.Tk()
root.title("Untitled - Notepad")
root.geometry("800x600")

# Create a scrolledText Widget for the main area
text_area = scrolledtext.ScrolledText(root, wrap=tk.WORD, undo=True, padx=5, pady=5)
text_area.pack(expand=True, fill="both")

# Create a Menu Bar
menu_bar = tk.Menu(root)
root.config(menu=menu_bar)

# Add file menu options
file_menu = tk.Menu(menu_bar, tearoff=0)
file_menu.add_command(label="New", command=new_file)
file_menu.add_command(label="Open", command=open_file)
file_menu.add_command(label="Save", command=save_file)
file_menu.add_separator()
file_menu.add_command(label="Exit", command=exit_app)
menu_bar.add_cascade(label="File", menu=file_menu)

# start the appplications main loop
root.mainloop()
