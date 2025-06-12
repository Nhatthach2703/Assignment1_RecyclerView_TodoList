# Assignment1 RecyclerView ToDoList

This Android application demonstrates two core features: a To-Do List manager and a RecyclerView demo. The project is designed for educational purposes, showcasing modern Android development practices using Java, RecyclerView, and custom adapters.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Installation & Getting Started](#installation--getting-started)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## Features

### To-Do List
- Add, edit, and delete tasks
- Mark tasks as complete/incomplete
- Assign deadlines to tasks (date format: yyyy-MM-dd)
- Filter and search tasks by status or keyword
- User-friendly interface with RecyclerView for task display

### RecyclerView Demo
- Display a list of items with images and names
- Click on an item to receive a Toast message
- Demonstrates RecyclerView usage and custom adapter implementation

## Project Structure

- `MainActivity.java`: Main screen with navigation buttons to access features
- `ToDoList.java`: Activity for managing the to-do list (add, edit, delete, filter, search)
- `RecyclerView.java`: Activity demonstrating a simple RecyclerView with clickable items
- `TaskAdapter.java`, `ItemAdapter.java`: RecyclerView adapters for tasks and items
- `model/Task.java`, `model/Item.java`: Data models for tasks and items
- `res/`: Layouts, drawables, and resources (icons, themes, strings, etc.)
- `build.gradle.kts`, `settings.gradle.kts`: Project build configuration

## Installation & Getting Started

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   ```
2. **Open the project in Android Studio.**
3. **Sync Gradle and build the project.**
4. **Run the app** on an emulator or a physical Android device (API 21+).

## Usage

- **Main Screen:**
  - Choose between "To-Do List" and "RecyclerView Demo".
- **To-Do List:**
  - Add a new task by entering a title and (optionally) a deadline.
  - Edit or delete existing tasks.
  - Mark tasks as complete/incomplete.
  - Use the search and filter options to find tasks quickly.
- **RecyclerView Demo:**
  - View a simple list of items with images.
  - Tap an item to see a Toast message with the item name.

## Requirements

- **Android Studio:** Arctic Fox or newer (recommended for best compatibility)
- **Android SDK:** Version 21 (Android 5.0 Lollipop) or higher
- **Java:** Version 8 or above

## Screenshots

*Add screenshots of the main screen, To-Do List, and RecyclerView demo here to illustrate the UI and features.*

## Detailed Usage Guide

### Main Screen
- Upon launching the app, you are presented with two buttons:
  - **To-Do List:** Opens the task management feature.
  - **RecyclerView Demo:** Opens a simple item list demo.

### To-Do List Feature
- **Add Task:**
  - Enter a task title and (optionally) a deadline.
  - Press the Add button to insert the task into your list.
- **Edit Task:**
  - Tap the Edit button next to a task to modify its title or deadline.
- **Delete Task:**
  - Tap the Delete button to remove a task from the list.
- **Mark as Complete/Incomplete:**
  - Tap the Complete button to toggle a task's completion status. Completed tasks may appear with a strikethrough or different color.
- **Search & Filter:**
  - Use the search bar to filter tasks by keyword.
  - Use the filter spinner to show all, only completed, or only incomplete tasks.
- **Task Deadlines:**
  - Deadlines are displayed next to each task. You can sort or filter tasks by deadline.

### RecyclerView Demo Feature
- Displays a vertical list of items, each with a name and an image.
- Tap any item to see a Toast message displaying the item's name.
- Demonstrates the use of RecyclerView, custom adapters, and click listeners.

## Code Highlights

- **Architecture:**
  - Follows a simple Activity-based structure for clarity and learning purposes.
  - Uses RecyclerView for efficient list rendering and user interaction.
- **Adapters:**
  - `TaskAdapter` manages the display and interaction logic for tasks.
  - `ItemAdapter` manages the item list in the demo.
- **Models:**
  - `Task` model includes title, completion status, creation time, and deadline.
  - `Item` model includes name and image resource ID.
- **UI:**
  - Uses XML layouts for activities and list items.
  - Includes custom icons for task actions (edit, delete, complete).

## How to Build and Run

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   ```
2. **Open in Android Studio:**
   - Select `Open an existing project` and choose this folder.
3. **Sync Gradle:**
   - Let Android Studio download dependencies and sync the project.
4. **Build and Run:**
   - Connect an Android device or start an emulator.
   - Click the Run button or use `Shift+F10`.

## Troubleshooting
- If you encounter build errors, ensure your Android Studio and SDK tools are up to date.
- Clean and rebuild the project if resources do not appear correctly.
- Check that your device/emulator meets the minimum SDK version.

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change. Contributions for bug fixes, documentation, or new features are appreciated.

## License

This project is for educational purposes only. You may use, modify, and share it freely for learning and teaching.
