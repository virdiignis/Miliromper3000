from django.contrib.auth.models import User
from django.db import models


class Ingredient(models.Model):
    name = models.CharField(max_length=50, primary_key=True)


class BartenderStuff(models.Model):
    name = models.CharField(max_length=50, primary_key=True)
    image = models.ImageField(upload_to="images/", null=True, blank=True)


class Glass(models.Model):
    name = models.CharField(max_length=50, primary_key=True)
    image = models.ImageField(upload_to="images", null=True, blank=True)


class Drink(models.Model):
    name = models.CharField(max_length=50)
    description = models.TextField()
    instruction = models.TextField(null=True, blank=True)
    how_to_serve = models.TextField(null=True, blank=True)
    stuff = models.ManyToManyField("BartenderStuff", null=True, blank=True)
    glass = models.ForeignKey(Glass, on_delete=models.SET_NULL, null=True)
    average_rating = models.DecimalField(decimal_places=1, max_digits=2, default=0.0, blank=True)


class IngredientProportion(models.Model):
    drink = models.ForeignKey(Drink, on_delete=models.CASCADE, related_name="ingredients")
    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE)
    amount = models.DecimalField(max_digits=5, decimal_places=1)
    unit = models.CharField(max_length=10)

    class Meta:
        unique_together = ('drink', 'ingredient')


class AlcoholProportion(models.Model):
    drink = models.ForeignKey(Drink, on_delete=models.CASCADE, related_name="alcohols")
    alcohol = models.ForeignKey("Alcohol", on_delete=models.CASCADE)
    amount = models.DecimalField(max_digits=5, decimal_places=1)
    unit = models.CharField(max_length=10)

    class Meta:
        unique_together = ('drink', 'alcohol')


class DrinkRating(models.Model):
    drink = models.ForeignKey(Drink, on_delete=models.CASCADE, related_name="ratings")
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name="drinkratings")
    rating = models.DecimalField(max_digits=2, decimal_places=1)
    favourite = models.BooleanField(default=False)

