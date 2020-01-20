from rest_framework.serializers import HyperlinkedModelSerializer, ModelSerializer
from dbAlco.models.shops import *


class ShopSerializer(ModelSerializer):
    class Meta:
        model = Shop
        fields = ('name', 'location')


class PubRatingSerializer(HyperlinkedModelSerializer):
    class Meta:
        model = PubRating
        fields = ('rating', 'user')


class PubSerializer(ModelSerializer):
    ratings = PubRatingSerializer(many=True, read_only=True)

    class Meta:
        model = Pub
        fields = ('name', 'location', 'ratings')


class PubOccurrenceSerializer(HyperlinkedModelSerializer):
    class Meta:
        model = PubOccurrence
        fields = ('alcohol', 'date', 'pub', 'price')


class ShopOccurrenceSerializer(HyperlinkedModelSerializer):
    class Meta:
        model = ShopOccurrence
        fields = ('alcohol', 'date', 'shop', 'price')
