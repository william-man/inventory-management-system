# inventory-management-system

A Java-based Inventory Management System that allows users to add, update, delete, search, and view items in an inventory. The project includes a graphical user interface (GUI) for easy interaction.

## Prerequisites

- Java Development Kit (JDK) 1.8 or higher
  - Developed using OpenJDK version 21.0.3
- Apache Maven 3.6.0 or higher (for building from source)

## Installation

### Using the Windows Executable

1. **Clone the repository**:

   ```sh
   git clone https://github.com/william-man/inventory-management-system.git
   cd your-repo-name

   ```

2. **At the root of the project directory run**:
   ```sh
   ./inventory-management.exe
   ```

### To build from source

1. **Clone the repository**:

   ```sh
   git clone https://github.com/william-man/inventory-management-system.git
   cd your-repo-name

   ```

2. **Build the project:**:

   ```sh
   mvn clean install

   ```

3. **Run the application**:
   ```sh
   java -jar target/inventory-management-1.0-SNAPSHOT-shaded.jar
   ```

## Usage

- Add Item: Allows users to add new items to the inventory.
- Delete Item: Allows users to delete existing items from the inventory.
- Update Item: Allows users to update details of existing items.
- Search Item: Allows users to search for items by ID.
- Show All Inventory: Displays all items in the inventory.
