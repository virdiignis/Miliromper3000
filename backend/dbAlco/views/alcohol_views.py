from django.db.models import Avg
from django.http import Http404, JsonResponse, HttpResponse
from dbAlco.models import Drink
from dbAlco.serializers.alcohol_serializers import *
from rest_framework import generics, viewsets, filters


class AlcoholViewSet(viewsets.ModelViewSet):
    queryset = Alcohol.objects.all()
    filter_backends = [filters.OrderingFilter]
    ordering = ['name']

    def get_serializer_class(self):
        if self.action in ("list", "retrieve"):
            return AlcoholGetSerializer
        else:
            return AlcoholModifySerializer


class AlcoholTypeViewSet(viewsets.ModelViewSet):
    queryset = AlcoholType.objects.all()
    serializer_class = AlcoholTypeSerializer
    filter_backends = [filters.OrderingFilter]


class AlcoholGeneralTypeViewSet(viewsets.ModelViewSet):
    queryset = AlcoholGeneralType.objects.all()
    serializer_class = AlcoholGeneralTypeSerializer
    filter_backends = [filters.OrderingFilter]


def alcohol_average_rating(request, _id):
    alcohol = Alcohol.objects.get(id=_id)
    avg_rating = alcohol.ratings.aggregate(Avg("rating"))

    return JsonResponse(avg_rating)
