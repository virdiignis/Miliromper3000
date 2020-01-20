from dbAlco.serializers.shops_serializers import *
from rest_framework import viewsets


class ShopViewSet(viewsets.ModelViewSet):
    queryset = Shop.objects.all()
    serializer_class = ShopSerializer


class PubRatingViewSet(viewsets.ModelViewSet):
    queryset = PubRating.objects.all()
    serializer_class = PubRatingSerializer


class PubViewSet(viewsets.ModelViewSet):
    queryset = Pub.objects.all()
    serializer_class = PubSerializer


class PubOccurrenceViewSet(viewsets.ModelViewSet):
    queryset = PubOccurrence.objects.all()
    serializer_class = PubOccurrenceSerializer


class ShopOccurrenceViewSet(viewsets.ModelViewSet):
    queryset = ShopOccurrence.objects.all()
    serializer_class = ShopOccurrenceSerializer
