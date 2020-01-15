from django.contrib.auth.models import User
from django.db import models


class Country(models.Model):
    name = models.CharField(max_length=128)


class Producer(models.Model):
    name = models.CharField(max_length=50)
    country = models.CharField(max_length=50)


class AlcoholRating(models.Model):
    alcohol = models.ForeignKey("Alcohol", on_delete=models.CASCADE)
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name="ratings")
    rating = models.DecimalField(decimal_places=1, max_digits=2)
    comment = models.TextField(null=True)
    favourite = models.BooleanField(default=False)

    class Meta:
        unique_together = ("alcohol", "user")


