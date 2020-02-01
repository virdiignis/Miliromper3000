from django.db.models import Avg
from django.http import JsonResponse

from dbAlco.serializers.drink_serializers import *
from rest_framework import viewsets


class DrinkViewSet(viewsets.ModelViewSet):
    queryset = Drink.objects.all()
    serializer_class = DrinkSerializer


class IngredientViewSet(viewsets.ModelViewSet):
    queryset = Ingredient.objects.all()
    serializer_class = IngredientSerializer


class BartenderStuffViewSet(viewsets.ModelViewSet):
    queryset = BartenderStuff.objects.all()
    serializer_class = BartenderStuffSerializer


class GlassViewSet(viewsets.ModelViewSet):
    queryset = Glass.objects.all()
    serializer_class = GlassSerializer


class IngredientProportionViewSet(viewsets.ModelViewSet):
    queryset = IngredientProportion.objects.all()
    serializer_class = IngredientProportionSerializer


class AlcoholProportionViewSet(viewsets.ModelViewSet):
    queryset = AlcoholProportion.objects.all()
    serializer_class = AlcoholProportionSerializer


class DrinkRatingViewSet(viewsets.ModelViewSet):
    queryset = DrinkRating.objects.all()
    serializer_class = DrinkRatingSerializer


def drink_average_rating(request, _id):
    drink = Drink.objects.get(id=_id)
    avg_rating = drink.ratings.aggregate(Avg("rating"))

    return JsonResponse(avg_rating)
