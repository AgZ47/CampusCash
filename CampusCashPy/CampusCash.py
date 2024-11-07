import tkinter as tk
from tkinter import messagebox
from pymongo import MongoClient
from bson import ObjectId
from PIL import Image, ImageTk
import ttkbootstrap as ttk

# Connect to MongoDB
client = MongoClient('mongodb+srv://22cs257:sonarf@cluster0.kyea5.mongodb.net/?retryWrites=true&w=majority')
db = client['test']  # database name
dues_collection = db['dues']  # collection name

class DueApp:
    def __init__(self, root, access_mode, login_window):
        self.root = root
        self.access_mode = access_mode  # Access mode controls whether it's 'add' or 'delete' mode
        self.login_window = login_window  # Store reference to login window

        self.root.title("Due Management")
        self.root.geometry("1280x720")  # Set the initial size to 1280x720
        self.root.resizable(True, True)  # Allow the window to be resizable

        # Set ttkbootstrap theme
        style = ttk.Style("cyborg")

        # Display logo images at the top
        logo_frame = ttk.Frame(root, padding=5)
        logo_frame.grid(row=0, column=0, columnspan=3, pady=(10, 0), padx=10, sticky="n")
        self.display_images(logo_frame)

        # Frame to hold input fields
        input_frame = ttk.Frame(root, padding=20)
        input_frame.grid(row=1, column=0, columnspan=2, pady=(10, 5), padx=10, sticky="ew")

        # Buttons based on access mode
        if self.access_mode == "store":
            ttk.Label(input_frame, text="Student ID:").grid(row=0, column=0, pady=5, padx=(5, 0), sticky="e")
            self.student_id_entry = ttk.Entry(input_frame, width=50)
            self.student_id_entry.grid(row=0, column=1, padx=(0, 10), pady=5, sticky="w")

            ttk.Label(input_frame, text="Description:").grid(row=1, column=0, pady=5, padx=(5, 0), sticky="e")
            self.description_entry = ttk.Entry(input_frame, width=50)
            self.description_entry.grid(row=1, column=1, padx=(0, 10), pady=5, sticky="w")

            ttk.Label(input_frame, text="Due Amount:").grid(row=2, column=0, pady=5, padx=(5, 0), sticky="e")
            self.due_amount_entry = ttk.Entry(input_frame, width=50)
            self.due_amount_entry.grid(row=2, column=1, padx=(0, 10), pady=5, sticky="w")

            self.add_button = ttk.Button(root, text="Add Due", bootstyle="info", command=self.add_due)
            self.add_button.grid(row=2, column=0, columnspan=2, pady=(5, 10))
        else:
            ttk.Label(input_frame, text="Filter by Student ID (Delete Mode):").grid(row=3, column=0, pady=5, sticky="e")
            self.filter_student_id_entry = ttk.Entry(input_frame, width=50)
            self.filter_student_id_entry.grid(row=3, column=1, padx=10, pady=5)

            self.filter_button = ttk.Button(root, text="Filter", bootstyle="info", command=self.filter_dues)
            self.filter_button.grid(row=3, column=0, columnspan=2, pady=(5, 10))

            self.delete_button = ttk.Button(root, text="Delete Selected Due", bootstyle="info", command=self.delete_due)
            self.delete_button.grid(row=4, column=0, columnspan=2, pady=(5, 10))

        self.back_button = ttk.Button(root, text="Back", bootstyle="info", command=self.go_back)
        self.back_button.grid(row=0, column=2, padx=10, pady=5, sticky="ne")

        # Listbox for dues with a scroll bar
        list_frame = ttk.Frame(root, padding=10, bootstyle="secondary")
        list_frame.grid(row=5, column=0, columnspan=2, padx=10, pady=(5, 10), sticky="nsew")
        root.grid_rowconfigure(5, weight=1)  # Make the list frame resizable vertically
        root.grid_columnconfigure(1, weight=1)  # Make the list frame resizable horizontally

        self.due_list = tk.Listbox(list_frame, width=150, height=10, font=('Helvetica', 10), selectbackground="#7289da")
        self.due_list.pack(side="left", fill="both", expand=True)

        scrollbar = ttk.Scrollbar(list_frame, orient="vertical", command=self.due_list.yview, bootstyle="round")
        scrollbar.pack(side="right", fill="y")
        self.due_list.config(yscrollcommand=scrollbar.set)

        self.load_dues()

    def display_images(self, parent_frame):
        try:
            logo_image = Image.open("logo.png").resize((400, 60), Image.LANCZOS)
            self.logo_photo = ImageTk.PhotoImage(logo_image)
            logo_label = tk.Label(parent_frame, image=self.logo_photo)
            logo_label.pack()

            second_image = Image.open("second_image.png").resize((175, 60), Image.LANCZOS)
            self.second_image_photo = ImageTk.PhotoImage(second_image)
            second_image_label = tk.Label(parent_frame, image=self.second_image_photo)
            second_image_label.pack()
        except Exception as e:
            print(f"Error loading images: {e}")

    def add_due(self):
        student_id = self.student_id_entry.get()
        description = self.description_entry.get()
        try:
            due_amount = float(self.due_amount_entry.get())
        except ValueError:
            messagebox.showerror("Invalid Input", "Due amount must be a number.")
            return

        if not student_id or not description:
            messagebox.showerror("Input Error", "Please fill in all fields.")
            return

        due = {"studentId": student_id, "description": description, "dueAmount": due_amount, "status": "pending"}
        dues_collection.insert_one(due)
        self.load_dues()
        self.student_id_entry.delete(0, tk.END)
        self.description_entry.delete(0, tk.END)
        self.due_amount_entry.delete(0, tk.END)
        messagebox.showinfo("Success", "Due added successfully!")

    def load_dues(self, student_id_filter=None):
        self.due_list.delete(0, tk.END)
        query = {"studentId": student_id_filter} if student_id_filter else {}
        dues = dues_collection.find(query)
        for due in dues:
            self.due_list.insert(tk.END, f"{due['_id']} - Student ID: {due['studentId']}, Due Amount: {due['dueAmount']}, Description: {due['description']}")

    def filter_dues(self):
        student_id_filter = self.filter_student_id_entry.get()
        self.load_dues(student_id_filter)

    def delete_due(self):
        selected_due = self.due_list.curselection()
        if not selected_due:
            messagebox.showerror("Selection Error", "No due selected for deletion.")
            return

        due_text = self.due_list.get(selected_due)
        due_id = due_text.split(" - ")[0]

        try:
            result = dues_collection.delete_one({"_id": ObjectId(due_id)})
            if result.deleted_count:
                messagebox.showinfo("Success", "Due deleted successfully!")
                self.filter_dues()
            else:
                messagebox.showerror("Error", "Due deletion failed.")
        except Exception as e:
            messagebox.showerror("Error", f"An error occurred: {e}")

    def go_back(self):
        self.root.destroy()
        self.login_window.deiconify()

class LoginApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Login")
        self.root.geometry("1280x720")

        style = ttk.Style("cyborg")

        self.logo_frame = ttk.Frame(root, padding=5)
        self.logo_frame.pack(pady=(5,0))
        self.display_images(self.logo_frame)

        self.store_credentials = {"storename": "store123", "password": "storepass"}
        self.staff_credentials = {"staffname": "staff123", "password": "staffpass"}
        self.is_store_login = True  # Start with store login by default

        # Title for login mode
        self.title_label = ttk.Label(root, text="Store Login", font=("Helvetica", 16, "bold"))
        self.title_label.pack(pady=(10, 0))

        self.login_frame = ttk.Frame(root, padding=20)
        self.login_frame.pack(pady=(0, 5))

        self.username_label = ttk.Label(self.login_frame, text="Username:")
        self.username_label.grid(row=0, column=0, pady=(0,5), sticky="e")
        self.username_entry = ttk.Entry(self.login_frame, width=50)
        self.username_entry.grid(row=0, column=1, pady=(0,5), sticky="w")

        self.password_label = ttk.Label(self.login_frame, text="Password:")
        self.password_label.grid(row=1, column=0, pady=(0,5), sticky="e")
        self.password_entry = ttk.Entry(self.login_frame, show="*", width=50)
        self.password_entry.grid(row=1, column=1, pady=(0,5), sticky="w")

        self.login_button = ttk.Button(root, text="Login", bootstyle="info", command=self.handle_login)
        self.login_button.pack(pady=5)

        self.toggle_button = ttk.Button(root, text="Switch to Staff Login", bootstyle="info", command=self.toggle_login_mode)
        self.toggle_button.pack(pady=5)

    def display_images(self, parent_frame):
        try:
            logo_image = Image.open("logo.png").resize((400, 60), Image.LANCZOS)
            self.logo_photo = ImageTk.PhotoImage(logo_image)
            logo_label = tk.Label(parent_frame, image=self.logo_photo)
            logo_label.pack()

            second_image = Image.open("second_image.png").resize((175, 60), Image.LANCZOS)
            self.second_image_photo = ImageTk.PhotoImage(second_image)
            second_image_label = tk.Label(parent_frame, image=self.second_image_photo)
            second_image_label.pack()
        except Exception as e:
            print(f"Error loading images: {e}")

    def handle_login(self):
        username = self.username_entry.get()
        password = self.password_entry.get()

        if self.is_store_login:
            if username == self.store_credentials["storename"] and password == self.store_credentials["password"]:
                self.open_due_management_app("store")
            else:
                messagebox.showerror("Error", "Invalid store credentials.")
        else:
            if username == self.staff_credentials["staffname"] and password == self.staff_credentials["password"]:
                self.open_due_management_app("staff")
            else:
                messagebox.showerror("Error", "Invalid staff credentials.")

    def open_due_management_app(self, access_mode):
        self.root.withdraw()
        due_management_window = tk.Toplevel(self.root)
        DueApp(due_management_window, access_mode, self.root)

    def toggle_login_mode(self):
        self.is_store_login = not self.is_store_login
        mode_text = "Store" if self.is_store_login else "Staff"
        self.title_label.config(text=f"{mode_text} Login")
        self.toggle_button.config(text=f"Switch to {'Staff' if self.is_store_login else 'Store'} Login")

if __name__ == "__main__":
    root = ttk.Window(themename="cyborg")
    app = LoginApp(root)
    root.mainloop()
