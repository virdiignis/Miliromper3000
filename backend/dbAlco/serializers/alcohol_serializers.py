from rest_framework.serializers import ModelSerializer
from dbAlco.models.alcohol import *


class AlcoholTypeSerializer(ModelSerializer):
    class Meta:
        model = AlcoholType
        fields = "__all__"


class AlcoholGetSerializer(ModelSerializer):
    type = AlcoholTypeSerializer(read_only=True)

    class Meta:
        model = Alcohol
        fields = "__all__"


class AlcoholModifySerializer(ModelSerializer):
    class Meta:
        model = Alcohol
        fields = "__all__"


class AlcoholGeneralTypeSerializer(ModelSerializer):
    class Meta:
        model = AlcoholGeneralType
        fields = "__all__"
