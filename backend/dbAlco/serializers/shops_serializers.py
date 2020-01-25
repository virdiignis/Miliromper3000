from rest_framework.serializers import HyperlinkedModelSerializer, ModelSerializer
from dbAlco.models.shops import *


class ShopSerializer(ModelSerializer):
    class Meta:
        model = Shop
        fields = "__all__"


class PubRatingSerializer(ModelSerializer):
    class Meta:
        model = PubRating
        fields = "__all__"


class PubSerializer(ModelSerializer):
    ratings = PubRatingSerializer(many=True, read_only=True)

    class Meta:
        model = Pub
        fields = "__all__"


class PubOccurrenceSerializer(ModelSerializer):
    class Meta:
        model = PubOccurrence
        fields = "__all__"


class ShopOccurrenceSerializer(ModelSerializer):
    class Meta:
        model = ShopOccurrence
        fields = "__all__"
