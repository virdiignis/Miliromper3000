from django.db.models import Avg
from django.http import JsonResponse

from dbAlco.serializers.drink_serializers import *
from rest_framework import viewsets, filters


class DrinkViewSet(viewsets.ModelViewSet):
    queryset = Drink.objects.all()
    serializer_class = DrinkSerializer
    filter_backends = [filters.OrderingFilter, filters.SearchFilter]
    ordering = ['name']
    search_fields = ['name', 'description', 'how_to_serve']


class IngredientViewSet(viewsets.ModelViewSet):
    queryset = Ingredient.objects.all()
    serializer_class = IngredientSerializer
    filter_backends = [filters.OrderingFilter, filters.SearchFilter]
    ordering = ['name']
    search_fields = ['name']


class BartenderStuffViewSet(viewsets.ModelViewSet):
    queryset = BartenderStuff.objects.all()
    serializer_class = BartenderStuffSerializer
    filter_backends = [filters.OrderingFilter, filters.SearchFilter]
    ordering = ['name']
    search_fields = ['name']


class GlassViewSet(viewsets.ModelViewSet):
    queryset = Glass.objects.all()
    serializer_class = GlassSerializer
    filter_backends = [filters.OrderingFilter, filters.SearchFilter]
    ordering = ['name']
    search_fields = ['name']


class IngredientProportionViewSet(viewsets.ModelViewSet):
    queryset = IngredientProportion.objects.all()
    serializer_class = IngredientProportionSerializer


class AlcoholProportionViewSet(viewsets.ModelViewSet):
    queryset = AlcoholProportion.objects.all()
    serializer_class = AlcoholProportionSerializer


class DrinkRatingViewSet(viewsets.ModelViewSet):
    queryset = DrinkRating.objects.all()
    serializer_class = DrinkRatingSerializer
    filter_backends = [filters.OrderingFilter, filters.SearchFilter]
    ordering = ['-favourite']
    search_fields = ['drink']


def drink_average_rating(request, _id):
    drink = Drink.objects.get(id=_id)
    avg_rating = drink.ratings.aggregate(Avg("rating"))

    return JsonResponse(avg_rating)
