from rest_framework.serializers import HyperlinkedModelSerializer, ModelSerializer
from dbAlco.models.drink import *


class IngredientSerializer(ModelSerializer):
    class Meta:
        model = Ingredient
        fields = ['name']


class BartenderStuffSerializer(ModelSerializer):
    class Meta:
        model = BartenderStuff
        fields = ['name', 'image']


class GlassSerializer(ModelSerializer):
    class Meta:
        model = Glass
        fields = ['name', 'image']


class IngredientProportionSerializer(ModelSerializer):
    class Meta:
        model = IngredientProportion
        fields = ('ingredient', 'amount', 'unit')


class AlcoholProportionSerializer(ModelSerializer):
    class Meta:
        model = AlcoholProportion
        fields = ('alcohol', 'amount', 'unit')


class DrinkRatingSerializer(ModelSerializer):
    class Meta:
        model = DrinkRating
        fields = ('drink', 'user', 'rating', 'favourite')


class DrinkSerializer(ModelSerializer):
    ingredients = IngredientProportionSerializer(many=True, read_only=True)
    alcohols = AlcoholProportionSerializer(many=True, read_only=True)
    ratings = DrinkRatingSerializer(many=True, read_only=True)

    class Meta:
        model = Drink
        fields = ('name', 'description', 'instruction', 'how_to_serve', 'stuff', 'glass', 'ingredients', 'alcohols', 'ratings')
