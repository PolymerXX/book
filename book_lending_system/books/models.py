from django.db import models

class Book(models.Model):
    """
    Model representing a book in the library
    """
    title = models.CharField(max_length=200)
    author = models.CharField(max_length=100)
    description = models.TextField()
    isbn = models.CharField(max_length=13)
    publisher = models.CharField(max_length=100)
    publish_date = models.DateField()
    cover_image = models.ImageField(upload_to='covers/', blank=True)
    stock = models.IntegerField(default=1)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    
    def __str__(self):
        return self.title
    
    def is_available(self):
        """Check if the book is available for borrowing"""
        return self.stock > 0
