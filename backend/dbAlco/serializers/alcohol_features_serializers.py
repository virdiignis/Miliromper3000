from rest_framework.serializers import HyperlinkedModelSerializer, ModelSerializer
from dbAlco.models.alcohol_features import *


class CountrySerializer(ModelSerializer):
    class Meta:
        model = Country
        fields = ['name']


class ProducerSerializer(ModelSerializer):
    class Meta:
        model = Producer
        fields = ['name', 'country']


class AlcoholRatingSerializer(ModelSerializer):
    class Meta:
        model = AlcoholRating
        fields = ['alcohol', 'user', 'rating', 'comment', 'favourite']
