from rest_framework.serializers import HyperlinkedModelSerializer, ModelSerializer
from dbAlco.models.alcohol import *


class AlcoholSerializer(ModelSerializer):
    class Meta:
        model = Alcohol
        fields = ['name', 'producer', 'alcohol_content', 'production_country', 'description', 'type']


class AlcoholTypeSerializer(ModelSerializer):
    class Meta:
        model = AlcoholType
        fields = ['genral_type', 'specific_type']


class AlcoholGeneralTypeSerializer(ModelSerializer):
    class Meta:
        model = AlcoholGeneralType
        fields = ['name']
