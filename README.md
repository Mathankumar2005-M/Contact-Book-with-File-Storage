# Contact Book with File Storage

A comprehensive contact management application that allows users to store, organize, and manage their contacts with persistent file storage capabilities.

## Features

### Core Functionality
- *Add Contacts*: Create new contact entries with detailed information
- *View Contacts*: Display all contacts in an organized, searchable format
- *Edit Contacts*: Modify existing contact information
- *Delete Contacts*: Remove unwanted contacts from the database
- *Search & Filter*: Find contacts quickly using various search criteria

### Contact Information Fields
- Full Name (First, Last, Middle)
- Phone Numbers (Home, Work, Mobile)
- Email Addresses (Personal, Work)
- Physical Address (Street, City, State, ZIP)
- Company/Organization
- Job Title
- Birthday
- Notes/Comments
- Profile Picture
- Social Media Links
- Emergency Contact Flag

### File Storage Features
- *Local File Storage*: Save contacts to JSON, CSV, or XML files
- *Import/Export*: Bulk import contacts from various file formats
- *Backup & Restore*: Create backups and restore from saved files
- *Data Synchronization*: Sync contacts across multiple devices
- *Version Control*: Track changes and maintain contact history

## Installation

### Prerequisites
- Python 3.8 or higher
- Required packages (install via pip):
  bash
  pip install -r requirements.txt
  

### Setup
1. Clone the repository:
   bash
   git clone https://github.com/username/contact-book.git
   cd contact-book
   

2. Install dependencies:
   bash
   pip install -r requirements.txt
   

3. Run the application:
   bash
   python main.py
   

## Usage

### Command Line Interface
bash
# Add a new contact
python contact_book.py add --name "John Doe" --phone "555-1234" --email "john@example.com"

# List all contacts
python contact_book.py list

# Search for contacts
python contact_book.py search --name "John"

# Export contacts to file
python contact_book.py export --format json --output contacts.json

# Import contacts from file
python contact_book.py import --file contacts.csv


### Graphical User Interface
Launch the GUI version:
bash
python gui_main.py


## File Storage Formats

### JSON Format
json
{
  "contacts": [
    {
      "id": "uuid-string",
      "firstName": "John",
      "lastName": "Doe",
      "phoneNumbers": {
        "mobile": "555-1234",
        "home": "555-5678"
      },
      "emails": {
        "personal": "john@example.com",
        "work": "john.doe@company.com"
      },
      "address": {
        "street": "123 Main St",
        "city": "Anytown",
        "state": "CA",
        "zip": "12345"
      },
      "company": "Example Corp",
      "jobTitle": "Software Developer",
      "birthday": "1990-05-15",
      "notes": "Met at conference",
      "dateCreated": "2024-01-01T10:00:00Z",
      "dateModified": "2024-01-02T15:30:00Z"
    }
  ]
}


### CSV Format
csv
ID,First Name,Last Name,Mobile,Home,Email,Company,Job Title,Birthday,Notes
uuid1,John,Doe,555-1234,555-5678,john@example.com,Example Corp,Developer,1990-05-15,Met at conference


## Configuration

### Settings File (config.json)
json
{
  "storage": {
    "defaultFormat": "json",
    "backupEnabled": true,
    "backupInterval": "daily",
    "maxBackups": 30
  },
  "display": {
    "theme": "light",
    "contactsPerPage": 50,
    "defaultSortBy": "lastName"
  },
  "security": {
    "encryptionEnabled": false,
    "passwordProtected": false
  }
}


## API Reference

### Core Classes

#### Contact Class
python
class Contact:
    def __init__(self, first_name, last_name, **kwargs):
        self.id = str(uuid.uuid4())
        self.first_name = first_name
        self.last_name = last_name
        # Additional fields...
    
    def to_dict(self):
        """Convert contact to dictionary"""
    
    def from_dict(cls, data):
        """Create contact from dictionary"""


#### ContactBook Class
python
class ContactBook:
    def add_contact(self, contact):
        """Add a new contact"""
    
    def get_contact(self, contact_id):
        """Retrieve contact by ID"""
    
    def update_contact(self, contact_id, **kwargs):
        """Update existing contact"""
    
    def delete_contact(self, contact_id):
        """Delete contact"""
    
    def search_contacts(self, query, field=None):
        """Search contacts"""
    
    def save_to_file(self, filename, format='json'):
        """Save contacts to file"""
    
    def load_from_file(self, filename):
        """Load contacts from file"""


### File Operations

#### Supported Import Formats
- JSON (.json)
- CSV (.csv)
- XML (.xml)
- vCard (.vcf)
- Excel (.xlsx)

#### Supported Export Formats
- JSON (.json)
- CSV (.csv)
- XML (.xml)
- vCard (.vcf)
- PDF (.pdf)
- Excel (.xlsx)

## Data Validation

The application includes comprehensive data validation:
- *Phone Numbers*: Validates format and removes invalid characters
- *Email Addresses*: Checks for valid email format
- *Dates*: Validates birthday and other date fields
- *Required Fields*: Ensures minimum required information is provided

## Security Features

- *Data Encryption*: Optional encryption for sensitive contact data
- *Password Protection*: Secure access to contact database
- *Backup Encryption*: Encrypted backup files
- *Access Logs*: Track who accessed contact information

## Testing

Run the test suite:
bash
python -m pytest tests/


Run specific test categories:
bash
# Unit tests
python -m pytest tests/unit/

# Integration tests
python -m pytest tests/integration/

# File storage tests
python -m pytest tests/storage/


## Contributing

1. Fork the repository
2. Create a feature branch (git checkout -b feature/new-feature)
3. Commit your changes (git commit -am 'Add new feature')
4. Push to the branch (git push origin feature/new-feature)
5. Create a Pull Request

### Code Style
- Follow PEP 8 guidelines
- Use meaningful variable names
- Include docstrings for all functions
- Write unit tests for new features

## Troubleshooting

### Common Issues

*File Permission Errors*
bash
# Ensure proper file permissions
chmod 644 contacts.json


*Import Errors*
- Check file format compatibility
- Verify file encoding (UTF-8 recommended)
- Ensure all required fields are present

*Performance Issues*
- Large contact lists may require pagination
- Consider using database storage for 10,000+ contacts
- Enable indexing for faster searches

## Changelog

### Version 2.1.0
- Added CSV import/export functionality
- Improved search algorithms
- Enhanced data validation
- Bug fixes for file handling

### Version 2.0.0
- Complete rewrite with modular architecture
- Added GUI interface
- Implemented backup/restore features
- Enhanced security options

### Version 1.0.0
- Initial release
- Basic CRUD operations
- JSON file storage
- Command-line interface

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For support and questions:
- Email: support@contactbook.com
- GitHub Issues: [Create an Issue](https://github.com/username/contact-book/issues)
- Documentation: [Wiki](https://github.com/username/contact-book/wiki)

## Acknowledgments

- Thanks to all contributors who helped improve this project
- Special thanks to the open-source community for inspiration and feedback
- Icons provided by [Heroicons](https://heroicons.com/)

---

*Note*: This application is designed for personal and small business use. For enterprise-level contact management, consider additional security and scalability measures.
