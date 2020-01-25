from rest_framework.serializers import ModelSerializer
from dbAlco.models.alcohol import *


class AlcoholTypeSerializer(ModelSerializer):
    class Meta:
        model = AlcoholType
        fields = "__all__"


class AlcoholSerializer(ModelSerializer):
    class Meta:
        model = Alcohol
        fields = "__all__"


class AlcoholGeneralTypeSerializer(ModelSerializer):
    class Meta:
        model = AlcoholGeneralType
        fields = "__all__"
