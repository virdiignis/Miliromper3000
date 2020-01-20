from dbAlco.serializers.alcohol_serializers import *
from rest_framework import generics, viewsets


class AlcoholViewSet(viewsets.ModelViewSet):
    queryset = Alcohol.objects.all()
    serializer_class = AlcoholSerializer


class AlcoholTypeViewSet(viewsets.ModelViewSet):
    queryset = AlcoholType.objects.all()
    serializer_class = AlcoholTypeSerializer


class AlcoholGeneralTypeViewSet(viewsets.ModelViewSet):
    queryset = AlcoholGeneralType.objects.all()
    serializer_class = AlcoholGeneralTypeSerializer
