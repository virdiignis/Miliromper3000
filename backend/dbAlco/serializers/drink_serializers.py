from rest_framework.serializers import HyperlinkedModelSerializer, ModelSerializer
from dbAlco.models.drink import *


class IngredientSerializer(ModelSerializer):
    class Meta:
        model = Ingredient
        fields = "__all__"


class BartenderStuffSerializer(ModelSerializer):
    class Meta:
        model = BartenderStuff
        fields = "__all__"


class GlassSerializer(ModelSerializer):
    class Meta:
        model = Glass
        fields = "__all__"


class IngredientProportionSerializer(ModelSerializer):
    class Meta:
        model = IngredientProportion
        fields = "__all__"


class AlcoholProportionSerializer(ModelSerializer):
    class Meta:
        model = AlcoholProportion
        fields = "__all__"


class DrinkRatingSerializer(ModelSerializer):
    class Meta:
        model = DrinkRating
        fields = "__all__"


class DrinkSerializer(ModelSerializer):
    ingredients = IngredientProportionSerializer(many=True, read_only=True)
    alcohols = AlcoholProportionSerializer(many=True, read_only=True)
    ratings = DrinkRatingSerializer(many=True, read_only=True)

    class Meta:
        model = Drink
        fields = (
        'name', 'description', 'instruction', 'how_to_serve', 'stuff', 'glass', 'ingredients', 'alcohols', 'ratings',
        "average_rating", "id")
