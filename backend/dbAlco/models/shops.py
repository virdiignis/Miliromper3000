from django.contrib.auth.models import User
from django.db import models


class Shop(models.Model):
    name = models.CharField(max_length=255)
    location = models.TextField()  # TODO: być może dodać geodjango


class Pub(models.Model):
    name = models.CharField(max_length=255)
    location = models.TextField()  # TODO: być może zastąpić geodjango


class PubRating(models.Model):
    rating = models.DecimalField(decimal_places=1, max_digits=2)
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    pub = models.ForeignKey(Pub, on_delete=models.CASCADE, related_name='ratings')


class PubOccurrence(models.Model):
    alcohol = models.ForeignKey("Alcohol", on_delete=models.CASCADE, related_name="pubprices")
    date = models.DateField()
    pub = models.ForeignKey(Pub, null=True, on_delete=models.CASCADE)
    price = models.DecimalField(decimal_places=2, max_digits=10)


class ShopOccurrence(models.Model):
    alcohol = models.ForeignKey("Alcohol", on_delete=models.CASCADE, related_name="shopprices")
    date = models.DateField()
    shop = models.ForeignKey(Shop, null=True, on_delete=models.CASCADE)
    price = models.DecimalField(decimal_places=2, max_digits=10)
