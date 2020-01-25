from rest_framework.serializers import HyperlinkedModelSerializer, ModelSerializer
from dbAlco.models.alcohol_features import *


class CountrySerializer(ModelSerializer):
    class Meta:
        model = Country
        fields = "__all__"


class ProducerSerializer(ModelSerializer):
    class Meta:
        model = Producer
        fields = "__all__"


class AlcoholRatingSerializer(ModelSerializer):
    class Meta:
        model = AlcoholRating
        fields = "__all__"
